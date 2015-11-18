package com.hsiang.example.svnkit;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;

public class SVNManagerTest {

    final String url = "http://192.168.1.1/svn" ;
    
    final String username = "username" ;
    
    final String password = "password" ;
    
    SVNManager svnManager = null ;
    
    @Before
    public void login() {
        svnManager = new SVNManager();
        svnManager.createSession(url, username, password);
    }

    @Test
    public void testGetLogs() {
        try {
            List<SVNLogEntry> logs = svnManager.getLogs(new Date());
            logs.forEach(
                log -> {
                    System.out.println(log.getRevision());    
                }
            );
            Assert.assertTrue(true);
        } catch (SVNException e) {
            Assert.fail();
        }
    }
    
    @Test
    public void testGetLogsWithAuthor() {
        try {
            List<SVNLogEntry> logs = svnManager.getLogs(new Date(), "aphsiang");
            logs.forEach(
                log -> {
                    System.out.println(log.getRevision());    
                }
            );
            Assert.assertTrue(true);
        } catch (SVNException e) {
            Assert.fail();
        }
    }
    
    @After
    public void close(){
        svnManager.closeSession();
    }
    
}
