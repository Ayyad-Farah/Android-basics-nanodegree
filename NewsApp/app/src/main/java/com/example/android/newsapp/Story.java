package com.example.android.newsapp;

import java.net.URL;

/**
 *An {@link Story} object contains all information related to a single story.
 *
 * Created by ayyad on 4/9/2017.
 */

public class Story {

    /** Title of the story */
    private String mTitle;

    /** web Publication Date of the story */
    private String mWebPublicationDate;

    /** URL for this story */
    URL mWebURL;

    /**
     * constructs new{@link Story} object.
     * @param title is the title of the story
     * @param webPublicationDate is the web publication date of the story.
     *@param webURL is the URL for the story.
     * */
    public Story (String title, String webPublicationDate, URL webURL) {
        mTitle = title;
        mWebPublicationDate = webPublicationDate;
        mWebURL = webURL;
    }

    /** Returns the title of the story */
    public String getTitle() {
        return mTitle;
    }

    /** Returns the web publication date of the story */
    public String getWebPublicationDate() {
        return mWebPublicationDate;
    }

    /** Returns the Url of the story */
    public URL getWebURL() {
        return mWebURL;
    }
}
