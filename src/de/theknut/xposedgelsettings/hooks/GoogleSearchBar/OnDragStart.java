package de.theknut.xposedgelsettings.hooks.GoogleSearchBar;

import static de.robv.android.xposed.XposedHelpers.getObjectField;
import static de.robv.android.xposed.XposedHelpers.setBooleanField;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import de.robv.android.xposed.XC_MethodHook;
import de.theknut.xposedgelsettings.hooks.Common;

public final class OnDragStart extends XC_MethodHook {
	
	// http://androidxref.com/4.4.2_r1/xref/packages/apps/Launcher3/src/com/android/launcher3/SearchDropTargetBar.java#187
	// public void onDragEnd()
	
	@Override
	protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
		
		// make the search bar invisible
		View qsb = (View) getObjectField(param.thisObject, "mQSBSearchBar");
		qsb.setAlpha(0f);
		
		// set the search bar to hidden
		setBooleanField(param.thisObject, "mIsSearchBarHidden", true);
	}
	
	@Override
	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
		// show the search bar
		GoogleSearchBarHooks.setLayoutParams(Common.LAUNCHER_INSTANCE, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Common.SEARCH_BAR_SPACE_WIDTH, Common.SEARCH_BAR_SPACE_HEIGHT);
	}
}