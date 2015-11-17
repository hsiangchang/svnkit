package com.hsiang.example.svnkit;

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
    public boolean login(String url, String username, String password) {
        try {
            // 設定儲存庫位址
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));

            // 設定儲存庫的帳號及密碼
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username,
                    password.toCharArray());
            repository.setAuthenticationManager(authManager);

            //嘗試連線，測試是否成功
            repository.testConnection();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
