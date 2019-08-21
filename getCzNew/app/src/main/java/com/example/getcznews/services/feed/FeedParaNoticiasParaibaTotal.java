package com.example.getcznews.services.feed;

import android.util.Log;

import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.domain.Fonte;
import com.example.getcznews.domain.Noticia;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para tratar a requisicao do XML do Paraiba Total
 * @author Antonio Miguel
 *
 */
public class FeedParaNoticiasParaibaTotal extends FeedParaNoticias {

    public FeedParaNoticiasParaibaTotal(NoticiaDAO noticiaDAO, Fonte fonte) {
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
                        noticia.setUrlImage("http://www.paraibatotal.com.br/static/imagens/principal/paraibatotal-logo.jpg");
                        insiderItem = true;
                    } else if (xpp.getName().equalsIgnoreCase(Tag.TITULO.value())) {
                        if (insiderItem) {
                            noticia.setTitulo(xpp.nextText());
                        }
                    } else if (xpp.getName().equalsIgnoreCase(Tag.DESCRICAO.value())) {
                        if(insiderItem){
                            noticia.setTexto(xpp.nextText());
                        }
                    }else if (xpp.getName().equalsIgnoreCase(Tag.LINK.value())) {
                        if(insiderItem){
                            fonte.setSite(xpp.nextText());
                            noticia.setFonte(fonte);
                        }
                    } else if(xpp.getName().equalsIgnoreCase(Tag.GUID.value())){
                        if(insiderItem){
                            xpp.nextText();
                        }

                    } else if(xpp.getName().equalsIgnoreCase(Tag.DATA.value())){
                        if(insiderItem){
                            xpp.nextText();
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase(Tag.ITEM.value())) {
                    insiderItem = false;
                    if (noticia != null) {
                        noticias.add(noticia);
//                        Log.e("Notícia",noticia.toString());
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
