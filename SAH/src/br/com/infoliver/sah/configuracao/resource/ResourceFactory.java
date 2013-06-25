package br.com.infoliver.sah.configuracao.resource;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class ResourceFactory {

	public static String getResourceFromKey(String resourceBase, String key) {
        String mensagem = null;
        try {
            mensagem = ResourceBundle.getBundle(resourceBase).getString(key);
        } catch (MissingResourceException ex) {
            ex.printStackTrace();
        }
        return mensagem;
    }

	public static String getResourceFromKey(String resourceBase, String key, Object... obj) {
        return MessageFormat.format(getResourceFromKey(resourceBase, key), obj);
    }
}
