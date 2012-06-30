package net.softforlife.klich.JSF.web.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

// TODO: Auto-generated Javadoc
/**
 * 
 * Esta clase implementa un listener de Fase de JSF ( Consultar documentación de
 * Jsf). Establece el borde en rojo cuando se sucede un error de validación
 * 
 * @author rtovar
 */
public class MessageListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 402393392745221244L;
	
	/** El ORIGINA l_ style. */
	private final String ORIGINAL_STYLE = "electro.faces.original.style";

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */

	public void beforePhase(PhaseEvent e) {
		FacesContext fc = e.getFacesContext();
		UIViewRoot root = fc.getViewRoot();
		Iterator <String> i = fc.getClientIdsWithMessages();
		restoreOriginalStyles(fc, root);
		while (i.hasNext()) {
			String clientId = (String) i.next();
			UIComponent c = root.findComponent(clientId);
			String fieldRef = (String) c.getAttributes().get("fieldRef");
			String msg;
			if (fieldRef != null) {
				msg = fieldRef;
			} else {
				String id = c.getId();
				msg = Messages.getString("messagesJSF", "required." + id, null);

				if (msg == null) {
					msg = id;
				}
			}

			Iterator <FacesMessage> j = fc.getMessages(clientId);
			boolean haveMessages = false;
			while (j.hasNext()) {
				FacesMessage fm = (FacesMessage) j.next();
				fm.setDetail(msg + ": " + fm.getDetail());
				haveMessages = true;
			}

			if (c instanceof HtmlInputText) {
				HtmlInputText inputText = (HtmlInputText) c;
				String styleClass = inputText.getStyleClass();
				if (haveMessages) {
					inputText.setStyleClass(styleClass + " inputError");
					saveOriginalStyle(clientId, styleClass, fc);

				}
			}
		}
	}

	/**
	 * Restore original styles.
	 * 
	 * @param context the context
	 * @param root the root
	 */
	@SuppressWarnings("rawtypes")
	private void restoreOriginalStyles(FacesContext context, UIViewRoot root) {
		Map session = context.getExternalContext().getSessionMap();

		if (session.containsKey(ORIGINAL_STYLE)) {

			@SuppressWarnings({ "unchecked" })
			List<Map> list = (List<Map>) session.get(ORIGINAL_STYLE);

			for (Map item : list) {
				Map.Entry entry = (Map.Entry) item.entrySet().iterator().next();
				UIComponent component = root.findComponent(entry.getKey()
						.toString());

				if (component != null) {
					component.getAttributes().put("styleClass",
							entry.getValue());
				}
			}

			session.remove(ORIGINAL_STYLE);
		}
	}

	/**
	 * Save original style.
	 * 
	 * @param id the id
	 * @param style the style
	 * @param context the context
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void saveOriginalStyle(String id, String style, FacesContext context) {
		Map session = context.getExternalContext().getSessionMap();

		Map<String, String> originalStyle = new HashMap<String, String>();

		originalStyle.put(id, style);

		if (session.get(ORIGINAL_STYLE) == null) {
			session.put(ORIGINAL_STYLE, new ArrayList<Map<String, String>>());
		}

		((List<Map<String, String>>) session.get(ORIGINAL_STYLE))
				.add(originalStyle);
	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	public void afterPhase(PhaseEvent e) {
	}
}

