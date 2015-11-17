package com.hsiang.example.svnkit;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;

public class SVNManagerTest {

    /**
     * 測試儲存庫是否能登入成功
     */
    @Test
    public void testLogin() {
        SVNManager svnManager = new SVNManager();
        boolean isSuccess = svnManager.createSession("http://192.168.0.1/svn/", "username", "password");
        Assert.assertEquals(Boolean.TRUE, isSuccess);
        svnManager.closeSession();
    }

    @Test
    public void testGetLogs() {
        SVNManager svnManager = new SVNManager();
        svnManager.createSession("http://192.168.0.1/svn/", "username", "password");
        try {
            List<SVNLogEntry> logs = svnManager.getLogs(new Date());
            logs.forEach(
                log -> {
                    System.out.println(log.getRevision());    
                }
            );
        } catch (SVNException e) {
            Assert.fail();
        }
        svnManager.closeSession();
    }
    
}
