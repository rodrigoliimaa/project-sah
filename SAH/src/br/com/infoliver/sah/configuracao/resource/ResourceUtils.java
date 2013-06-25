package br.com.infoliver.sah.configuracao.resource;

public class ResourceUtils {
    private static String resourceBase = ResourceUtils.class.getPackage().getName() + ".MsgResource_pt_BR";

    public static String getResourceFromKey(String key) {
        return ResourceFactory.getResourceFromKey(resourceBase, key);
    }

    public static String getResourceFromKey(String key, Object... obj) {
        return ResourceFactory.getResourceFromKey(resourceBase, key, obj);
    }
}
