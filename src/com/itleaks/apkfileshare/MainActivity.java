package com.itleaks.apkfileshare;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity {

	private static final String TAG = "Itleaks test";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		readFirstApkFile();
	}

	private void readFirstApkFile() {
		// TODO Auto-generated method stub
        List<PackageInfo> installedList = this.getPackageManager().getInstalledPackages(0);
        int installedListSize = installedList.size();
        ApplicationInfo firstApplicationInfo = null;
        for(int i = 0; i < installedListSize; i++) {
            PackageInfo info = installedList.get(i);
            ApplicationInfo aInfo = info.applicationInfo;
            Log.d(TAG, "application source dir " + aInfo.sourceDir); 
            if (firstApplicationInfo == null) {
            	firstApplicationInfo = aInfo;
            }
        }
        File file = new File(firstApplicationInfo.sourceDir);
        if (!file.exists()) {
        	Log.e(TAG, "package:" + firstApplicationInfo.packageName
        			+ " Apk file " + firstApplicationInfo.sourceDir + " doesn't exist");
        } else {
        	FileInputStream in = null;
			try {
				in = new FileInputStream(file);
				int size;
				try {
					size = in.available();
		        	Log.d(TAG, "Apk file " + firstApplicationInfo.sourceDir + " size:" + size);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
}
