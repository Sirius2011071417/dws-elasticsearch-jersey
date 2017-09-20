package cn.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ESClient {
	private static TransportClient client;
	static {
		Settings settings = Settings.builder()
                .put("cluster.name", "zhongbiao_test").build();
		try {
			client = new PreBuiltTransportClient(settings)
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.93.128.201"), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
	public static TransportClient getClient() {
		return client;
	}
}
