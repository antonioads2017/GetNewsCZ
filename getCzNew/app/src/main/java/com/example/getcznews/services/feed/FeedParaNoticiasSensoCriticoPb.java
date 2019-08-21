package com.example.getcznews.services.feed;

import android.text.Html;
import android.util.Log;

import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.domain.Fonte;
import com.example.getcznews.domain.Noticia;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para tratar a requisicao do XML do Senso Critico PB
 * @author Antonio Miguel
 *
 */
public class FeedParaNoticiasSensoCriticoPb extends FeedParaNoticias {
    public FeedParaNoticiasSensoCriticoPb(NoticiaDAO noticiaDAO, Fonte fonte) {
        super(noticiaDAO, fonte);
    }

    @Override
    protected List<Noticia> xmlParaNoticias(XmlPullParser xpp) {
        List<Noticia> noticias = new ArrayList<>();
        try {
            Noticia noticia = null;

            boolean insiderItem = false;

            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equalsIgnoreCase(Tag.ITEM.value())) {
                        noticia = new Noticia();
                        insiderItem = true;
                    } else if (xpp.getName().equalsIgnoreCase(Tag.TITULO.value())) {
                        if (insiderItem) {
                            noticia.setTitulo(xpp.nextText());
                        }
                    }else if(xpp.getName().equalsIgnoreCase(Tag.DATA.value())){
                        if(insiderItem){
                            xpp.nextText();
                        }
                    } else if (xpp.getName().equalsIgnoreCase(Tag.CATEGORIA.value())) {
                        if(insiderItem){
                            xpp.nextText();
                        }
                    }else if (xpp.getName().equalsIgnoreCase(Tag.DESCRICAO.value())) {
                        if(insiderItem){
                            xpp.nextText();
                        }
                    }else if (xpp.getName().equalsIgnoreCase(Tag.IMAGEM.value())) {
                        if(insiderItem){
                            noticia.setUrlImage(xpp.nextText());
//                            Log.e("Imagem",noticia.getUrlImage());
                        }
                    }else if (xpp.getName().equalsIgnoreCase(Tag.CONTEUDO.value())) {
                        if(insiderItem){
                            String html = xpp.nextText();
                            noticia.setTexto(Html.fromHtml(html).toString());
                        }
                    }else if(xpp.getName().equalsIgnoreCase(Tag.GUID.value())){
                        if(insiderItem){
                            xpp.nextText();
                        }
                    }else if (xpp.getName().equalsIgnoreCase(Tag.LINK.value())) {
                        if(insiderItem){
                            fonte.setSite(xpp.nextText());
                            noticia.setFonte(fonte);
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase(Tag.ITEM.value())) {
                    insiderItem = false;
                    if (noticia != null) {
//                        Log.e("Notícia",noticia.toString());
                        noticias.add(noticia);

                    }
                }


                eventType = xpp.next();
            }
        }catch (Exception e) {
            Log.e("ExceptionRss","Erro no tratamento do XML da classe "+this.getClass().getName());
        }

        return noticias;
    }
}
