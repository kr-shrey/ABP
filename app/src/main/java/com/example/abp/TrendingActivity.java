package com.example.abp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.example.abp.*;

public class TrendingActivity extends Activity implements AdapterView.OnItemClickListener {
    	public final String RSSFEEDOFCHOICE = "http://www.ibm.com/developerworks/views/rss/customrssatom.jsp?zone_by=XML&zone_by=Java&zone_by=Rational&zone_by=Linux&zone_by=Open+source&zone_by=WebSphere&type_by=Tutorials&search_by=&day=1&month=06&year=2007&max_entries=20&feed_by=rss&isGUI=true&Submit.x=48&Submit.y=14";
    //public final String RSSFEEDOFCHOICE = "http://citysolution.co.in/home/feed";

    public final String tag = "Trending";
    private RSSFeed feed = null;

    /** Called when the activity is first created. */

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_trending);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        // go get our feed!
        feed = getFeed(RSSFEEDOFCHOICE);
        Log.i(tag,"getting feed!!");
        // display UI
        UpdateDisplay();

    }


    private RSSFeed getFeed(String urlToRssFeed)
    {
        try
        {
            // setup the url
            URL url = new URL(urlToRssFeed);

            // create the factory
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // create a parser
            SAXParser parser = factory.newSAXParser();

            // create the reader (scanner)
            XMLReader xmlreader = parser.getXMLReader();
            // instantiate our handler
            RSSHandler theRssHandler = new RSSHandler();
            // assign our handler
            xmlreader.setContentHandler(theRssHandler);
            // get our data via the url class
            InputSource is = new InputSource(url.openStream());
            // perform the synchronous parse
            xmlreader.parse(is);
            // get the results - should be a fully populated RSSFeed instance, or null on error
            return theRssHandler.getFeed();
        }
        catch (Exception ee)
        {
            Log.i(tag,ee.toString());
            System.err.println(ee.toString());
            // if we have a problem, simply return null
            return null;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);

        menu.add(0,0,0, "Choose RSS Feed");
        menu.add(0, 1, 1, "Refresh");
        Log.i(tag, "onCreateOptionsMenu");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case 0:
                Log.i(tag,"Set RSS Feed");
                return true;
            case 1:
                Log.i(tag,"Refreshing RSS Feed");
                return true;
        }
        return false;
    }


    private void UpdateDisplay()
    {
        TextView feedtitle = (TextView) findViewById(R.id.feedtitle);
        TextView feedpubdate = (TextView) findViewById(R.id.feedpubdate);
        ListView itemlist = (ListView) findViewById(R.id.itemlist);


        if (feed == null)
        {
            feedtitle.setText("No RSS Feed Available");
            return;
        }

        feedtitle.setText(feed.getTitle());
        feedpubdate.setText(feed.getPubDate());

        ArrayAdapter<RSSItem> adapter = new ArrayAdapter<RSSItem>(this,android.R.layout.simple_list_item_1,feed.getAllItems());

        itemlist.setAdapter(adapter);

        itemlist.setOnItemClickListener(this);

        itemlist.setSelection(0);

    }


    public void onItemClick(AdapterView parent, View v, int position, long id)
    {
        Log.i(tag,"item clicked! [" + feed.getItem(position).getTitle() + "]");

        Intent itemintent = new Intent(this, com.example.abp.ShowDescription.class);

        Bundle b = new Bundle();
        b.putString("title", feed.getItem(position).getTitle());
        b.putString("description", feed.getItem(position).getDescription());
        b.putString("link", feed.getItem(position).getLink());
        b.putString("pubdate", feed.getItem(position).getPubDate());

        itemintent.putExtra("android.intent.extra.INTENT", b);

        startActivityForResult(itemintent,0);
    }
}