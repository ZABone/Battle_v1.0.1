package com.wangxia.battle.presenter.impPresenter;

import com.wangxia.battle.callback.ISuccessCallbackData;
import com.wangxia.battle.model.model.VideoDetailModel;
import com.wangxia.battle.presenter.IPresenter;
import com.wangxia.battle.presenter.callback.ICallback;


/**
 * 视频详情
 *
 */
public class VideoDetailPresenter implements IPresenter,ICallback {
    private VideoDetailModel videoDetailModel = new VideoDetailModel();
    private ISuccessCallbackData iAppInfo;

    public VideoDetailPresenter(ISuccessCallbackData iAppInfo) {
        this.iAppInfo = iAppInfo;
    }

    public void queryList(int id ,String args,int pageNo){
        videoDetailModel.queryList(id,args,pageNo, this);
    }

    @Override
    public void success(Object appInfo,int type) {
          iAppInfo.getResult(appInfo,type);
    }

    @Override
    public void error( ) {
        iAppInfo.errorRequest();
    }

    @Override
    public void fail() {
        iAppInfo.failRequest();}

}
