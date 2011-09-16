package org.urlshortener.web.client;

import com.google.gwt.user.client.rpc.impl.RemoteServiceProxy;
import com.google.gwt.user.client.rpc.impl.ClientSerializationStreamWriter;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.impl.RequestCallbackAdapter.ResponseReader;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.RpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.core.client.impl.Impl;
import com.google.gwt.user.client.rpc.impl.RpcStatsContext;

public class ShorteringUrlService_Proxy extends RemoteServiceProxy implements org.urlshortener.web.client.ShorteringUrlServiceAsync {
  private static final String REMOTE_SERVICE_INTERFACE_NAME = "org.urlshortener.web.client.ShorteringUrlService";
  private static final String SERIALIZATION_POLICY ="FC4CA916212B35313490B7C89218B8A8";
  private static final org.urlshortener.web.client.ShorteringUrlService_TypeSerializer SERIALIZER = new org.urlshortener.web.client.ShorteringUrlService_TypeSerializer();
  
  public ShorteringUrlService_Proxy() {
    super(GWT.getModuleBaseURL(),
      "shorten", 
      SERIALIZATION_POLICY, 
      SERIALIZER);
  }
  
  public void shortenUrlServer(java.lang.String input, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    RpcStatsContext statsContext = new RpcStatsContext();
    boolean toss = statsContext.isStatsAvailable() && statsContext.stats(statsContext.timeStat("ShorteringUrlService_Proxy.shortenUrlServer", "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      if (getRpcToken() != null) {
        streamWriter.writeObject(getRpcToken());
      }
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("shortenUrlServer");
      streamWriter.writeInt(1);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(input);
      String payload = streamWriter.toString();
      toss = statsContext.isStatsAvailable() && statsContext.stats(statsContext.timeStat("ShorteringUrlService_Proxy.shortenUrlServer",  "requestSerialized"));
      doInvoke(ResponseReader.STRING, "ShorteringUrlService_Proxy.shortenUrlServer", statsContext, payload, callback);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  @Override
  public SerializationStreamWriter createStreamWriter() {
    ClientSerializationStreamWriter toReturn =
      (ClientSerializationStreamWriter) super.createStreamWriter();
    if (getRpcToken() != null) {
      toReturn.addFlags(ClientSerializationStreamWriter.FLAG_RPC_TOKEN_INCLUDED);
    }
    return toReturn;
  }
  @Override
  protected void checkRpcTokenType(RpcToken token) {
    if (!(token instanceof com.google.gwt.user.client.rpc.XsrfToken)) {
      throw new RpcTokenException("Invalid RpcToken type: expected 'com.google.gwt.user.client.rpc.XsrfToken' but got '" + token.getClass() + "'");
    }
  }
}
