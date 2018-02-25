package com.example.android.puretoneinfo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToWeb(View view) {
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://puretone.net"));
        startActivity(webIntent);
    }

    public void callUs(View view) {
        String phone = getString(R.string.phoneNumber);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        startActivity(callIntent);
    }

    public void emailUs(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:info@puretone.net"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry via Android App");
        startActivity(emailIntent);
    }

    public void facebook(View view) {
        final String urlFb = "fb://pg/PuretoneLtd";
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        facebookIntent.setData(Uri.parse(urlFb));
        // If Facebook application is installed, use that else launch a browser
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(facebookIntent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() == 0) {
            final String urlBrowser = "https://www.facebook.com/pg/PuretoneLtd";
            facebookIntent.setData(Uri.parse(urlBrowser));
        }
        startActivity(facebookIntent);
    }

    public void twitter(View view) {
        // Create intent using ACTION_VIEW and a normal Twitter url:
        String tweetUrl = String.format("https://twitter.com/puretoneltd");
        Intent twitIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));

// Narrow down to official Twitter app, if available:
        List<ResolveInfo> matches = getPackageManager().queryIntentActivities(twitIntent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                twitIntent.setPackage(info.activityInfo.packageName);
            }
        }

        startActivity(twitIntent);
    }
    public void geo(View view) {
        Intent geoLocationIntent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:51.395048, 0.512960?q=Puretone"));
        geoLocationIntent.setPackage("com.google.android.apps.maps");

        startActivity(geoLocationIntent);
    }
}

