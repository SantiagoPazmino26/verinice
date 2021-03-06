/*******************************************************************************
 * Copyright (c) 2016 Sebastian Hagedorn.
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Sebastian Hagedorn sh[at]sernet.de - initial API and implementation
 ******************************************************************************/
package sernet.verinice.report.service.impl.security.permissionhandling;

import java.security.Permission;
import java.util.List;
import java.util.Map;

/**
 * @author Sebastian Hagedorn sh[at]sernet.de
 *
 */
public class DefaultPermissionHandler extends AbstractPermissionHandler {
    
    private Map<String, List<String>> allowedPermissionsAndActionsMap;
    
    public DefaultPermissionHandler(Map<String, List<String>> permissionActionMap){
        this.allowedPermissionsAndActionsMap = permissionActionMap;
    }

    @Override
    public void handlePermission(Permission permission) {
        List<String> allowedActions = allowedPermissionsAndActionsMap.get(permission.getClass().getCanonicalName());
        for (String allowedAction : allowedActions){
            if (allowedAction.equals(permission.getName())){
                return;
            }
        }
        throwSecurityException(permission);

    }

}
