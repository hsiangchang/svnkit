package com.hsiang.example.svnkit;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * 這個物件提供 SVN 相關管理及應用的實作
 *
 * @author ChienHsiang.Chang@gmail.com (HSIANG CHANG)
 */
public class SVNManager {

    SVNRepository repository;

    /**
     * 連線至儲存庫
     * @param url 儲存庫位址
     * @param username 帳號
     * @param password 密碼
     * @return boolean
     */
    public boolean createSession(String url, String username, String password) {
        try {
            // 設定儲存庫位址
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));

            // 設定儲存庫的帳號及密碼
            ISVNAuthenticationManager authManager = 
                SVNWCUtil.createDefaultAuthenticationManager(username, password.toCharArray());
            repository.setAuthenticationManager(authManager);

            //嘗試連線，測試是否成功
            repository.testConnection();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 取得指定日期的相關異動
     * @param date
     * @return
     * @throws SVNException
     */
    @SuppressWarnings("unchecked")
    public List<SVNLogEntry> getLogs(Date date) throws SVNException {
        Calendar yesterday = Calendar.getInstance();
        yesterday.setTime(date);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);
        
        Calendar inDate = Calendar.getInstance();
        inDate.setTime(date);
        inDate.set(Calendar.HOUR_OF_DAY, 23);
        inDate.set(Calendar.MINUTE, 59);
        inDate.set(Calendar.SECOND, 59);
        
        long startRevision = repository.getDatedRevision(yesterday.getTime()) + 1;
        long endRevision = repository.getDatedRevision(inDate.getTime());

        return (List<SVNLogEntry>) repository.log(new String[] { "" }, null, startRevision, endRevision, true, true);
    }
    
    /**
     * 
     */
    public void closeSession() {
        repository.closeSession();
    }

}
