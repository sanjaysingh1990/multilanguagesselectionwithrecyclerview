package com.example.android.multilanguageselectdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.android.multilanguageselectdemo.itemtype.NotSelectedItem;
import com.example.android.multilanguageselectdemo.itemtype.SearchBox;
import com.example.android.multilanguageselectdemo.itemtype.SelectedItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CallBackLanguageSelected {
    RecyclerView recyclerView;
    List<Object> mItemList;
    LinearLayoutManager layoutManager;
    FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameLayout= (FrameLayout) findViewById(R.id.container);

        getCounteriesList();
        init();
    }

    private void getCounteriesList() {
        String jsonString = "[ \n" +
                "         \"Afghanistan\",\n" +
                "         \"Albania\",\n" +
                "         \"Algeria\",\n" +
                "         \"American Samoa\",\n" +
                "         \"Andorra\",\n" +
                "         \"Angola\",\n" +
                "         \"Anguilla\",\n" +
                "         \"Antarctica\",\n" +
                "         \"Antigua and Barbuda\",\n" +
                "         \"Argentina\",\n" +
                "         \"Armenia\",\n" +
                "         \"Aruba\",\n" +
                "         \"Australia\",\n" +
                "         \"Austria\",\n" +
                "         \"Azerbaijan\",\n" +
                "         \"Bahamas\",\n" +
                "         \"Bahrain\",\n" +
                "         \"Bangladesh\",\n" +
                "         \"Barbados\",\n" +
                "         \"Belarus\",\n" +
                "         \"Belgium\",\n" +
                "         \"Belize\",\n" +
                "         \"Benin\",\n" +
                "         \"Bermuda\",\n" +
                "         \"Bhutan\",\n" +
                "         \"Bolivia, Plurinational State of\",\n" +
                "         \"Bosnia and Herzegovina\",\n" +
                "         \"Botswana\",\n" +
                "         \"Brazil\",\n" +
                "         \"Brunei Darussalam\",\n" +
                "         \"Bulgaria\",\n" +
                "         \"Burkina Faso\",\n" +
                "         \"Burundi\",\n" +
                "         \"Cambodia\",\n" +
                "         \"Cameroon\",\n" +
                "         \"Canada\",\n" +
                "         \"Cape Verde\",\n" +
                "         \"Cayman Islands\",\n" +
                "         \"Central African Republic\",\n" +
                "         \"Chad\",\n" +
                "         \"Chile\",\n" +
                "         \"China\",\n" +
                "         \"Colombia\",\n" +
                "         \"Comoros\",\n" +
                "         \"Congo\",\n" +
                "         \"Congo, the Democratic Republic of the\",\n" +
                "         \"Cook Islands\",\n" +
                "         \"Costa Rica\",\n" +
                "         \"Côte d\\\"Ivoire\",\n" +
                "         \"Croatia\",\n" +
                "         \"Cuba\",\n" +
                "         \"Curaçao\",\n" +
                "         \"Cyprus\",\n" +
                "         \"Czech Republic\",\n" +
                "         \"Denmark\",\n" +
                "         \"Djibouti\",\n" +
                "         \"Dominica\",\n" +
                "         \"Dominican Republic\",\n" +
                "         \"Ecuador\",\n" +
                "         \"Egypt\",\n" +
                "         \"England\",\n" +
                "         \"El Salvador\",\n" +
                "         \"Equatorial Guinea\",\n" +
                "         \"Eritrea\",\n" +
                "         \"Estonia\",\n" +
                "         \"Ethiopia\",\n" +
                "         \"Falkland Islands (Malvinas)\",\n" +
                "         \"Faroe Islands\",\n" +
                "         \"Fiji\",\n" +
                "         \"Finland\",\n" +
                "         \"France\",\n" +
                "         \"French Guiana\",\n" +
                "         \"French Polynesia\",\n" +
                "         \"French Southern Territories\",\n" +
                "         \"Gabon\",\n" +
                "         \"Gambia\",\n" +
                "         \"Georgia\",\n" +
                "         \"Germany\",\n" +
                "         \"Ghana\",\n" +
                "         \"Gibraltar\",\n" +
                "         \"Greece\",\n" +
                "         \"Greenland\",\n" +
                "         \"Grenada\",\n" +
                "         \"Guadeloupe\",\n" +
                "         \"Guam\",\n" +
                "         \"Guatemala\",\n" +
                "         \"Guernsey\",\n" +
                "         \"Guinea\",\n" +
                "         \"Guinea-Bissau\",\n" +
                "         \"Guyana\",\n" +
                "         \"Haiti\",\n" +
                "         \"Holy See (Vatican City State)\",\n" +
                "         \"Honduras\",\n" +
                "         \"Hong Kong\",\n" +
                "         \"Hungary\",\n" +
                "         \"Iceland\",\n" +
                "         \"India\",\n" +
                "         \"Indonesia\",\n" +
                "         \"Iran, Islamic Republic of\",\n" +
                "         \"Iraq\",\n" +
                "         \"Ireland\",\n" +
                "         \"Isle of Man\",\n" +
                "         \"Israel\",\n" +
                "         \"Italy\",\n" +
                "         \"Jamaica\",\n" +
                "         \"Japan\",\n" +
                "         \"Jordan\",\n" +
                "         \"Kazakhstan\",\n" +
                "         \"Kenya\",\n" +
                "         \"Kiribati\",\n" +
                "         \"Korea, Democratic People\\\"s Republic of\",\n" +
                "         \"Korea, Republic of\",\n" +
                "         \"Kuwait\",\n" +
                "         \"Kyrgyzstan\",\n" +
                "         \"Lao People\\\"s Democratic Republic\",\n" +
                "         \"Latvia\",\n" +
                "         \"Lebanon\",\n" +
                "         \"Lesotho\",\n" +
                "         \"Liberia\",\n" +
                "         \"Libya\",\n" +
                "         \"Liechtenstein\",\n" +
                "         \"Lithuania\",\n" +
                "         \"Luxembourg\",\n" +
                "         \"Macao\",\n" +
                "         \"Macedonia, the Former Yugoslav Republic of\",\n" +
                "         \"Madagascar\",\n" +
                "         \"Malawi\",\n" +
                "         \"Malaysia\",\n" +
                "         \"Maldives\",\n" +
                "         \"Mali\",\n" +
                "         \"Malta\",\n" +
                "         \"Marshall Islands\",\n" +
                "         \"Martinique\",\n" +
                "         \"Mauritania\",\n" +
                "         \"Mauritius\",\n" +
                "         \"Mayotte\",\n" +
                "         \"Mexico\",\n" +
                "         \"Micronesia, Federated States of\",\n" +
                "         \"Moldova, Republic of\",\n" +
                "         \"Monaco\",\n" +
                "         \"Mongolia\",\n" +
                "         \"Montenegro\",\n" +
                "         \"Montserrat\",\n" +
                "         \"Morocco\",\n" +
                "         \"Mozambique\",\n" +
                "         \"Myanmar\",\n" +
                "         \"Namibia\",\n" +
                "         \"Nauru\",\n" +
                "         \"Nepal\",\n" +
                "         \"Netherlands\",\n" +
                "         \"Northern Ireland\",\n" +
                "         \"New Caledonia\",\n" +
                "         \"New Zealand\",\n" +
                "         \"Nicaragua\",\n" +
                "         \"Niger\",\n" +
                "         \"Nigeria\",\n" +
                "         \"Niue\",\n" +
                "         \"Norfolk Island\",\n" +
                "         \"Northern Mariana Islands\",\n" +
                "         \"Norway\",\n" +
                "         \"Oman\",\n" +
                "         \"Pakistan\",\n" +
                "         \"Palau\",\n" +
                "         \"Palestine, State of\",\n" +
                "         \"Panama\",\n" +
                "         \"Papua New Guinea\",\n" +
                "         \"Paraguay\",\n" +
                "         \"Peru\",\n" +
                "         \"Philippines\",\n" +
                "         \"Poland\",\n" +
                "         \"Portugal\",\n" +
                "         \"Qatar\",\n" +
                "         \"Réunion\",\n" +
                "         \"Romania\",\n" +
                "         \"Russian Federation\",\n" +
                "         \"Rwanda\",\n" +
                "         \"Saint Helena, Ascension and Tristan da Cunha\",\n" +
                "         \"Saint Kitts and Nevis\",\n" +
                "         \"Saint Lucia\",\n" +
                "         \"Saint Pierre and Miquelon\",\n" +
                "         \"Saint Vincent and the Grenadines\",\n" +
                "         \"Samoa\",\n" +
                "         \"San Marino\",\n" +
                "         \"Sao Tome and Principe\",\n" +
                "         \"Saudi Arabia\",\n" +
                "         \"Scotland\",\n" +
                "         \"Senegal\",\n" +
                "         \"Serbia\",\n" +
                "         \"Seychelles\",\n" +
                "         \"Sierra Leone\",\n" +
                "         \"Singapore\",\n" +
                "         \"Slovakia\",\n" +
                "         \"Slovenia\",\n" +
                "         \"Solomon Islands\",\n" +
                "         \"Somalia\",\n" +
                "         \"South Africa\",\n" +
                "         \"Spain\",\n" +
                "         \"Sri Lanka\",\n" +
                "         \"Sudan\",\n" +
                "         \"Suriname\",\n" +
                "         \"Svalbard and Jan Mayen\",\n" +
                "         \"Swaziland\",\n" +
                "         \"Sweden\",\n" +
                "         \"Switzerland\",\n" +
                "         \"Syrian Arab Republic\",\n" +
                "         \"Taiwan, Province of China\",\n" +
                "         \"Tajikistan\",\n" +
                "         \"Tanzania, United Republic of\",\n" +
                "         \"Thailand\",\n" +
                "         \"Togo\",\n" +
                "         \"Tokelau\",\n" +
                "         \"Tonga\",\n" +
                "         \"Trinidad and Tobago\",\n" +
                "         \"Tunisia\",\n" +
                "         \"Turkey\",\n" +
                "         \"Turkmenistan\",\n" +
                "         \"Turks and Caicos Islands\",\n" +
                "         \"Tuvalu\",\n" +
                "         \"Uganda\",\n" +
                "         \"Ukraine\",\n" +
                "         \"United Arab Emirates\",\n" +
                "         \"United States\",\n" +
                "         \"Uruguay\",\n" +
                "         \"Uzbekistan\",\n" +
                "         \"Vanuatu\",\n" +
                "         \"Venezuela, Bolivarian Republic of\",\n" +
                "         \"Viet Nam\",\n" +
                "         \"Virgin Islands, British\",\n" +
                "         \"Wallis and Futuna\",\n" +
                "         \"Wales\",\n" +
                "         \"Western Sahara\",\n" +
                "         \"Yemen\",\n" +
                "         \"Zambia\",\n" +
                "         \"Zimbabwe\"\n" +
                "      ]";
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> contactList = gson.fromJson(jsonString, type);
        mItemList = new ArrayList<Object>();
        mItemList.add(new SearchBox());
        for (String str : contactList) {
            mItemList.add(new NotSelectedItem(str));
        }

        Log.e("size", contactList.size() + "");
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //add items


        ListAdapter mAdapter = new ListAdapter(this,mItemList,this);
        recyclerView.setAdapter(mAdapter);
    }

    public void scrollToTop(int pos) {
        recyclerView.scrollToPosition(pos);
    }

    public void moveto(int pos) {
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.scrollToPositionWithOffset(pos+1, 60);
    }

    @Override
    public void languageSelected(String value) {
        LanguageLevelFragment fragment=LanguageLevelFragment.newInstance();
        String tag = fragment.getClass().getSimpleName();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_in, R.anim.anim_out, R.anim.anim_in_reverse_frag, R.anim.anim_out_reverse_frag);
        transaction.addToBackStack(tag);
        transaction.add(R.id.container, fragment, tag)
                .commitAllowingStateLoss();
    }
}
