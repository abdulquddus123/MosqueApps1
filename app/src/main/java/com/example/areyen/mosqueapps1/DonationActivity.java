package com.example.areyen.mosqueapps1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DonationActivity extends AppCompatActivity {
    View section1, section2, section3, section4, section5, section6;
    Button home;
    ImageButton twiter,facebook;
    TextView iccuk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        iccuk=(TextView)findViewById(R.id.iccuk);
        iccuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.iccuk.org/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        twiter=(ImageButton)findViewById(R.id.twiterLink);
        twiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://twitter.com/iccukorg");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }
        });

        facebook=(ImageButton)findViewById(R.id.facebookLink);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://www.facebook.com/iccuk.org");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }
        });

        home=(Button)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Toast.makeText(DonationActivity.this, "Testing", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });
        section1 = findViewById(R.id.section1);
        section2 = findViewById(R.id.section2);
        section3 = findViewById(R.id.section3);
        section4 = findViewById(R.id.section4);
        section5 = findViewById(R.id.section5);
        section6 = findViewById(R.id.section6);

        View header1 = findViewById(R.id.header1);
        header1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section1.getVisibility() == View.GONE)
                {
                    section1.setVisibility(View.VISIBLE);
                    section2.setVisibility(View.GONE);
                    section3.setVisibility(View.GONE);
                    section4.setVisibility(View.GONE);
                    section5.setVisibility(View.GONE);
                    section6.setVisibility(View.GONE);
                }
                else
                {
                    section1.setVisibility(View.GONE);
                }
            }
        });

        View header2 = findViewById(R.id.header2);
        header2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section2.getVisibility() == View.GONE)
                {
                    section2.setVisibility(View.VISIBLE);
                    section1.setVisibility(View.GONE);

                    section3.setVisibility(View.GONE);
                    section4.setVisibility(View.GONE);
                    section5.setVisibility(View.GONE);
                    section6.setVisibility(View.GONE);
                }
                else
                {
                    section2.setVisibility(View.GONE);
                }
            }
        });

        View header3 = findViewById(R.id.header3);
        header3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section3.getVisibility() == View.GONE)
                {
                    section3.setVisibility(View.VISIBLE);
                    section1.setVisibility(View.GONE);
                    section2.setVisibility(View.GONE);

                    section4.setVisibility(View.GONE);
                    section5.setVisibility(View.GONE);
                    section6.setVisibility(View.GONE);
                }
                else
                {
                    section3.setVisibility(View.GONE);
                }
            }
        });
        View header4 = findViewById(R.id.header4);
        header4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section4.getVisibility() == View.GONE)
                {
                    section4.setVisibility(View.VISIBLE);
                    section1.setVisibility(View.GONE);
                    section2.setVisibility(View.GONE);
                    section3.setVisibility(View.GONE);

                    section5.setVisibility(View.GONE);
                    section6.setVisibility(View.GONE);
                }
                else
                {
                    section4.setVisibility(View.GONE);
                }
            }
        });
        View header5 = findViewById(R.id.header5);
        header5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section5.getVisibility() == View.GONE)
                {
                    section5.setVisibility(View.VISIBLE);
                    section1.setVisibility(View.GONE);
                    section2.setVisibility(View.GONE);
                    section3.setVisibility(View.GONE);
                    section4.setVisibility(View.GONE);

                    section6.setVisibility(View.GONE);
                }
                else
                {
                    section5.setVisibility(View.GONE);
                }
            }
        });
        View header6 = findViewById(R.id.header6);
        header6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section6.getVisibility() == View.GONE)
                {
                    section6.setVisibility(View.VISIBLE);
                    section1.setVisibility(View.GONE);
                    section2.setVisibility(View.GONE);
                    section3.setVisibility(View.GONE);
                    section4.setVisibility(View.GONE);
                    section5.setVisibility(View.GONE);

                }
                else
                {
                    section6.setVisibility(View.GONE);
                }
            }
        });
    }

    public void donateLink(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_zakat"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void donateFitr(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_zakatalfitr"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void donateSadaqah(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_sadaqah"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void donateFidya(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_fidya"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void donateKaffarah(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_fidya"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void donateMosque(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_mosque"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void donateZakat(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page.php?section=donate&page=donate_zakat"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}