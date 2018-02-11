package cn.go.core.service.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import cn.go.core.service.product.SerachService;

/**
 * 接收MQ中的消息 
 * @author lx
 *
 */
public class CustomMessageListener  implements MessageListener{

	@Autowired
	private SerachService serachService;
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		ActiveMQTextMessage amtm  = (ActiveMQTextMessage)message;
		try {
			String id = amtm.getText();
			System.out.println("接收到的ID:" + id);
			serachService.insertProductToSolr(Long.parseLong(id));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
