package net.softforlife.klich.service.msg;

import java.util.ArrayList;
import java.util.List;

import net.softforlife.klich.service.UserService;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(value = "service")
public class ViewMessageServiceImpl implements ViewMessageService {

	private UserService userService;

	@Override
	public void addMessage(int severity, String msg) {
		addMessage(severity, msg, null);
	}

	@Override
	public void addMessage(int severity, String msg, String[] args) {

		List<Message> msgs = getViewMessages();
		Message message = new Message(severity, msg, args);
		msgs.add(message);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getViewMessages() {
		Object msgsObject = userService.getSessionProperty(SYSTEM_MSG);
		List<Message> msgs = null;
		if (msgsObject == null) {
			msgs = new ArrayList<Message>();
			userService.setSessionProperty(SYSTEM_MSG, msgs);
		} else {
			msgs = (ArrayList<Message>) msgsObject;
		}

		return msgs;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}