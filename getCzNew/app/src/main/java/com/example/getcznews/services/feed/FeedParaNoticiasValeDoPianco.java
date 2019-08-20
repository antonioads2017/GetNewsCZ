package com.example.getcznews.services.feed;


import android.util.Log;
import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.domain.Fonte;
import com.example.getcznews.domain.Noticia;
import org.xmlpull.v1.XmlPullParser;

public class FeedParaNoticiasValeDoPianco extends FeedParaNoticias {

    public FeedParaNoticiasValeDoPianco(NoticiaDAO noticiaDAO, String urlFeed) {
        super(noticiaDAO, urlFeed);
    }

    @Override
    protected void xmlParaNoticias(XmlPullParser xpp) {
        try {

            Fonte fonte = new Fonte();
            fonte.setId(1);
            fonte.setFeed("Vale do Piancó Noticias");

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
                    } else if (xpp.getName().equalsIgnoreCase(Tag.LINK.value())) {
                        if(insiderItem){
                            fonte.setSite(xpp.nextText());
                            noticia.setFonte(fonte);
                        }
                    } else if(xpp.getName().equalsIgnoreCase(Tag.GUID.value())){
                        if(insiderItem){
                            xpp.nextText();
                        }

                    } else if (xpp.getName().equalsIgnoreCase(Tag.DESCRICAO.value())) {
                        if(insiderItem){
                            noticia.setTexto(xpp.nextText());
                        }
                    }else if(xpp.getName().equalsIgnoreCase(Tag.DATA.value())){
                        if(insiderItem){
                            xpp.nextText();
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase(Tag.ITEM.value())) {
                    insiderItem = false;
                    if (noticia != null) {
                        Log.e("Notícia",noticia.toString());
                        getNoticias().add(noticia);
                    }
                }


                eventType = xpp.next();
            }
        }catch (Exception e) {
        }

    }
    private enum Tag {
        ITEM("item"),
        TITULO("title"),
        LINK("link"),
        GUID("guid"),
        DESCRICAO("description"),
        DATA("pubDate");

        private final String value;

        Tag(String value){
            this.value=value;
        }

        public String value(){
            return this.value;
        }
    }
}



