package com.maybrightventures.lender.dao.interfaces;

/**
 * Created by arindamnath on 28/12/15.
 */
public interface ServerResponseListener {
    void onSuccess(int threadId);
    void onFaliure();
}
