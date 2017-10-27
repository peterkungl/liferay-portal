package com.liferay.journal.upgrade;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserModel;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class UpgradeUser extends UpgradeProcess{

    private static final Log _log = LogFactoryUtil.getLog(
            UpgradeUser.class);

    @Override
    protected void doUpgrade() throws Exception {
        UserLocalServiceUtil util = new UserLocalServiceUtil();
        for (User user : util.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
            String screenName = user.getScreenName();
            _log.info(screenName);
        }

    }
}
