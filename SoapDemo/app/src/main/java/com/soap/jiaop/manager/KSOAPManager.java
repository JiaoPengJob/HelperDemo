package com.soap.jiaop.manager;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by jiaop on 2016/6/2.
 */
public class KSOAPManager {

    private String nameSpace = "http://ws.apache.org/axis2";
    private String endPoint = "http://115.28.247.40:8090/axis2/services/ManageJSONService";
    private String actionName;
    private String result;

    public KSOAPManager(String nameSpace,String endPoint,String actionName) {
        super();
        this.nameSpace = nameSpace;
        this.endPoint = endPoint;
        this.actionName = actionName;
    }

    class WSAsyncTask extends AsyncTask<Map<String, Object>, Integer, String> {

        @Override
        protected String doInBackground(Map<String, Object>... arg0) {
            try {
                result = buildExecInfor(arg0[0]);
                return result;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

    public void execInfor(Map<String, Object> map) {
        new WSAsyncTask().execute(map);
    }

    private String buildExecInfor(Map<String, Object> map) {
        SoapObject rpc = new SoapObject(nameSpace, actionName);
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            Object val = (Object) entry.getValue();
            rpc.addProperty(key, val);
        }
        return postWebserviceRequest(rpc);
    }

    private String postWebserviceRequest(SoapObject rpc) {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER10);
        envelope.bodyOut = rpc;
        envelope.dotNet = true;
        HttpTransportSE transport = new HttpTransportSE(endPoint);
        try {
            transport.call(nameSpace + "/" + actionName, envelope);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object object = (Object) envelope.bodyIn;
        String r = object.toString();
        String s = r.substring(r.lastIndexOf("=") + 1, r.length() - 3);
        return s;
    }

    public String getResult() {
        return result;
    }

}
