/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.crashpick.acra;

import java.io.IOException;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import com.pan.learn.crashpick.R;

import android.app.Application;

/**
 * Created by panhongchao on 16/3/4.
 */
//@ReportsCrashes(formUri = "http://yourserver.com/yourscript",
//        mode = ReportingInteractionMode.TOAST,
//        forceCloseDialogAfterToast = false, // optional, default false
//        resToastText = R.string.crash_toast_text)

@ReportsCrashes(formUri = "http://yourserver.com/yourscript",
        mode = ReportingInteractionMode.DIALOG,
        resToastText = R.string.crash_toast_text, // optional, displayed as soon as the crash occurs, before collecting data which can take a few seconds
        resDialogText = R.string.crash_dialog_text,
        resDialogIcon = android.R.drawable.ic_dialog_info, //optional. default is a warning sign
        resDialogTitle = R.string.crash_dialog_title, // optional. default is your application name
        resDialogCommentPrompt = R.string.crash_dialog_comment_prompt, // optional. When defined, adds a user text field input with this text resource as a label
        resDialogOkToast = R.string.crash_dialog_ok_toast // optional. displays a Toast message when the user accepts to send a report.
)
public class AcraApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ACRA.init(this);
    }
}
