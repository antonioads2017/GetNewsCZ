package com.example.getcznews.services.feed;

import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.domain.Fonte;
import com.example.getcznews.domain.Noticia;
import com.example.getcznews.services.TimeView;
import org.xmlpull.v1.XmlPullParser;
import java.util.List;

/**
 * Classe Abstrata responsavel por
 * gerenciar os feeds das noticias
 * @author Antonio Miguel
 * @author Isleimar
 */
public abstract class FeedParaNoticias {

    protected Fonte fonte;
    protected NoticiaDAO noticiaDAO;


    /**
     * Metodo abstrata do tratamento do xml das noticias
     * @param xpp responsavel por tratar o xml
     * @return Lista de Noticias
     */
    protected  abstract List<Noticia> xmlParaNoticias(XmlPullParser xpp);

    public FeedParaNoticias(NoticiaDAO noticiaDAO, Fonte fonte) {
        this.fonte = fonte;
        this.noticiaDAO = noticiaDAO;

        //Chama o método abstract para ler notícias do site
        new ProcessInBackground(this).execute();
    }

    /**
     * Metodo para salvar as noticias requisitadas
     * no banco de dados local e enviar para TimeView
     * @param noticias  - Lista de Noticias
     */
    protected void persistirNoticias(List<Noticia> noticias){
        if (noticias.size() == 0)
            return;
        noticiaDAO.limpar(fonte.getId());
        for (Noticia noticia: noticias) {
            noticiaDAO.salvar(noticia);
        }

        TimeView.run();

    }

    /**
     * Enum das Tags do XML dos feeds
     * @author Antonio Miguel
     */
    protected enum Tag {
        ITEM("item"),
        TITULO("title"),
        LINK("link"),
        GUID("guid"),
        DESCRICAO("description"),
        DATA("pubDate"),
        IMAGEM("image"),
        CATEGORIA("category"),
        CONTEUDO("content:encoded");

        private final String value;

        Tag(String value){
            this.value=value;
        }

        public String value(){
            return this.value;
        }
    }

}
