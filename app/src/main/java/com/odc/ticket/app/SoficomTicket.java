package com.odc.ticket.app;

import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;
import com.odc.ticket.data.models.UserModel;
import com.odc.ticket.data.prefs.LocalData;
import com.odc.ticket.tools.LocalConstants;


public class SoficomTicket extends MultiDexApplication {
    private static SoficomTicket _instance = null;

    public static SoficomTicket getInstance(){
        return _instance;
    }

    @Nullable
    private UserModel model;

    @Override
    public void onCreate() {
        super.onCreate();
        LocalData localData = new LocalData(this);
        if(localData.getString(LocalConstants.TICKET_URL)==null){
            localData.setString(LocalConstants.TICKET_URL, "tickets.soficomit.com");
        }
        if(localData.getString(LocalConstants.CLIENT_URL)==null){
            localData.setString(LocalConstants.CLIENT_URL, "clients.soficomit.com");
        }
       // ObjectBox.init(this);
        _instance = this;
    }

    public UserModel getUser() {
        return model;
    }

    public void setModel(@Nullable UserModel model) {
        this.model = model;
    }
}
