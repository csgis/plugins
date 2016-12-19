package org.geoladris.auth;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.geoladris.PortalRequestConfiguration;
import org.geoladris.config.ModuleConfigurationProvider;

import net.sf.json.JSONObject;

public class AuthConfigurationProvider implements ModuleConfigurationProvider {
  public static final String MODULE_NAME = "auth-user";

  @Override
  public Map<String, JSONObject> getPluginConfig(PortalRequestConfiguration configurationContext,
      HttpServletRequest request) throws IOException {
    JSONObject pluginConfig = new JSONObject();
    if (Auth.isAuthorized(request)) {
      String userName = request.getUserPrincipal().getName();
      pluginConfig.put(MODULE_NAME, JSONObject.fromObject("{ user : '" + userName + "'}"));
    }

    return Collections.singletonMap(Auth.PLUGIN_NAME, pluginConfig);
  }

  @Override
  public boolean canBeCached() {
    return false;
  }
}