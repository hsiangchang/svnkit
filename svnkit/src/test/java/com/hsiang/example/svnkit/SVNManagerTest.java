package com.hsiang.example.svnkit;

import org.junit.Assert;
import org.junit.Test;

public class SVNManagerTest {

    /**
     * 測試儲存庫是否能登入成功
     */
    @Test
    public void testLogin() {
        SVNManager svnManager = new SVNManager();
        boolean isSuccess = svnManager.login("https://192.168.1.1/svn", "demo", "123456");
        Assert.assertEquals(Boolean.TRUE, isSuccess);
    }

}
