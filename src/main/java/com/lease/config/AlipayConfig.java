package com.lease.config;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091600525254";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQCnqKgb2o40khaPYTV9CP0mp/uI4TseSAL/KVHhEvGWwXun2iuNAw3rKOsFlrKeqgeuaFAbLnmZomgHQ5pZWsLBS1Enj32icSKryuhAhLlv78FCdQ7rh9PZ6zoPTPq1rpa4qEEAnRxGt+3/UUdW2WaHVD8DXGct4Szm9lW7GmeCoPJ/wBqt/oDE4KN1MKM+jgSaLFU5hd7Wb/4ZZ6jfc7xh/A3lFVQaSxh9V1pk44JPmC1Gi4NAmZS4WuwA43d2qvQXFsa78SwskC+mKRV0n9ps9HH4Gw5U0W6UndCNKsMGonnBccVevSFfOib1zjojKxiN9zKPLAmiHMcVa5Y0cFbfAgMBAAECggEBAKQiMclq8jK3Hgzp7rJkvWg9pbtAPF8NwplIO1dr80VST5tos6AN8G9alJLZEC7SaEiu6FVj7zmfy9Xosc9uUiFneaCQEj+YIIgsUajwBpZ2Ei3caWndhGHfrexSwVJiBD3swg42wPrej8mXKY71sDEmIbZJnN1Gh23olM9iLxmqnynMP0KdzVgiWOr+s4JymMQ6c1cGCxZFQLqowKZsjIdyj5u8QgWu61M4egX9j5RnSHls2j8+bZV2kG8fePYMcm/WJzUnh6mPegjm25e9Ug8BZQRttymS8Fx2ZF0N7i+3ohuYEx7V2X44dXm4QFEEcpHFv6KszA72tApBcSjULxECgYEA12shIAL4wXKUa82gdSb6lAe8UqAUMzMw/fqRf0b1q0O/jAHu9rrRKP1aoFWEogC1wKxJDQTmqNr/UkKP8e5bLLElXixY5723O/cK8Jhm/7sqZPCfNqenmNg93WAk07mj6GUEmBFUx5NH/2D52aIVAybqyRgQyK4tp5pty90++CkCgYEAxz4/eeiSwtxmXT5o85SzXhg5a2SlSi667r1vxo6YoxVfv2KlSVClOFCCjKKRC5e+d08kFCHtoNDI0lYLsyAHcdVqa2ywyHwM1CEB2i/3ZMpEQlp8kwWWW4ipB2+NfzyQXssz2Tk+7q63/gkufzcB2wK+kVcrBxDNpWhqZBPb18cCgYEAiFIaD0jyJHEe7a2dqiV1kzHmAh+dSN1PVpo3cuTz2GKyF6atk2ObsyXJMQeNPHvYiMo+HEgKs7aKtoK61u3wsrKTpCnKRTSwfPkT6jd3jsPhyyjeiFbGX5oQZERQac/jotAyfQ0Sqo1QlZLH6eqBA5KgJ4vcKzLWX2cBrm4fSCkCgYEAn8gmxZAi6gwwN0TOi9DLi4Enwx0ihPw2wzC+S+CdPePtNiFY5hO7fA7ssvXIFiDqCOo5x79xvs7QzgVdOphMABkjT+UJXK5G53NU9fITlwuyc/f3ATyw2NUeoKzwL4KVUL/q46BJ/MW3Hg2k8TZe0ZFA3HOoOPFbyfOTppBadI8CgYEAw2FGEUAhQq5kbkS/T3e+aZIehG7fi9evhJEr8jmSMPSizo3kNZJE3R6DC7gtgBXas1tSJr5bDprfpRDGp7TjEH2DV+MOUtoqEwWoBsf6KNttIryl0HBYmaJ2kIkMtHCrLdfhwRIb7Iyb6355pZ4erVJuZNm0kDBj7l6QdRJ0u9g=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtGwi/tGqmGZKP30Gpx6PnBsKQi6j45pAIdbt12emCwn+qXI8gUcsFEi/P91F5NAYiufbIH1GMiWdG7MOwOSJJ3Yq7vhpXSbGkQEWfyABn78IxNFzmzs0eDDQtjT9coNrcMkhofOgoJ1qRwjioXvU1BEtQG2aD4IU3kewMArKGOspAXBQtbwXPwRvCr61/U2k0qyJJOJ0N4htMHUG/ISkrEhfqUxFUiJUEuAO8nmJgBzUZDOkjJ8BTARbvrw7+Xl4aIm8dJJgfMWeYeo71D95ftl0wq1NLFi36BTEIfB6BleaNQf4A4N57G9Jh14Lyzvo+Lo3uHliT1u6jWwfT68jnwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
	public static String notify_url = "http://localhost:8081/testnotify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//	public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
	public static  String return_url ="http://localhost:8081/returnPersonal";
	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public  void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

