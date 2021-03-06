/* Copyright 2013 David Axmark

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.mosync.nativeui.util;

import com.mosync.internal.android.MoSyncThread;
import com.mosync.internal.generated.IX_WIDGET;
import com.mosync.nativeui.ui.widgets.ScreenWidget;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Utility class for screen transitions
 *
 * @author Mircea Vasiliniuc
 */
final public class ScreenTransitions {
	/*
	 * When adding a new screen transition make sure you modify the
     * ScreenTransitions::isScreenTransitionAvailable method so that it
     * validates the new transitions as available on Android.
	 */

	/*
	 * Applies screen transitions according to the parameters.
	 *
	 * @param aView the view that will be involved in the transition.
	 * @param aScreenTransitionType the type of the screen transition.
	 * @param aScreenTransitionDuration the duration of the screen transition.
	 * @param isTransitionInStackView true if the transition must be made in a stack screen, false otherwise.
	 */
	static public void applyScreenTransition(View aView, int aScreenTransitionType, int aScreenTransitionDuration, Boolean isTransitionInStackView)
	{
        switch ( aScreenTransitionType )
        {
            // When adding a new screen transition make sure you modify the
            // ScreenTransitions::isScreenTransitionAvailable method so that it
            // validates the new transitions as available on Android.
            case IX_WIDGET.MAW_TRANSITION_TYPE_SLIDE_LEFT:
                applyScreenTransitionSlideLeft(aView, aScreenTransitionDuration);
                break;
            case IX_WIDGET.MAW_TRANSITION_TYPE_SLIDE_RIGHT:
                applyScreenTransitionSlideRight(aView, aScreenTransitionDuration);
                break;
            case IX_WIDGET.MAW_TRANSITION_TYPE_FADE_IN:
                applyScreenTransitionFadeIn(aView, aScreenTransitionDuration);
                break;
            case IX_WIDGET.MAW_TRANSITION_TYPE_FADE_OUT:
                // Get the current views
                ScreenWidget currentScreen = null;
                if ( !isTransitionInStackView )
                {
                    currentScreen = MoSyncThread.getInstance().getUnconvertedCurrentScreen();
                }
                else
                {
                    currentScreen = MoSyncThread.getInstance().getCurrentScreen();
                }

                if ( null == currentScreen )
                {
                    Log.i("MoSync", "doScreenTransition, currentScreen is null.");
                }
                else
                {
                    applyScreenTransitionFadeOut(currentScreen.getView(), aScreenTransitionDuration);
                }
                break;
            default:
                if ( null != aView )
                {
                    aView.clearAnimation();
                }
                // Apply no transition. The IX_WIDGET.MAW_TRANSITION_TYPE_NONE case.
                break;
        }
	}

	/*
	 * Checks if the screen transition given as parameter is available on Android.
	 * Screen transition types can be found \link #MA_TRANSITION_TYPE_NONE here \endlink.
	 *
	 * @param screenTransitionType a screen transition type.
	 *
	 * @return True if the screen transition given as parameter is available on Android, false otherwise.
	 */
	static public Boolean isScreenTransitionAvailable(int screenTransitionType)
	{
		// TODO Find a better solution to validate platform availability for screen transitions.
        switch ( screenTransitionType )
        {
            case IX_WIDGET.MAW_TRANSITION_TYPE_SLIDE_LEFT:
            case IX_WIDGET.MAW_TRANSITION_TYPE_SLIDE_RIGHT:
            case IX_WIDGET.MAW_TRANSITION_TYPE_FADE_IN:
            case IX_WIDGET.MAW_TRANSITION_TYPE_FADE_OUT:
            case IX_WIDGET.MAW_TRANSITION_TYPE_NONE:
                return true;
            default:
                return false;
        }
	}

	/*
	 * A transition that will show the next screen by sliding it from right to left.
	 *
	 * @param aView the view that will be involved in the transition.
	 * @param aScreenTransitionDuration the duration of the screen transition.
	 */
	static private void applyScreenTransitionSlideLeft(View aView, int aScreenTransitionDuration)
	{
		if ( null == aView )
		{
			return;
		}

		Animation screenTransition = null;
		aView.clearAnimation();

		screenTransition = AnimationUtils.makeInAnimation(aView.getContext(), false);
	    screenTransition.setDuration(aScreenTransitionDuration);

	    aView.startAnimation(screenTransition);
	}

	/*
	 * A transition that will show the next screen by sliding it from left to right.
	 *
	 * @param aView the view that will be involved in the transition.
	 * @param aScreenTransitionDuration the duration of the screen transition.
	 */
	static private void applyScreenTransitionSlideRight(View aView, int aScreenTransitionDuration)
	{
		if ( null == aView )
		{
			return;
		}

		Animation screenTransition = null;
		aView.clearAnimation();

	    screenTransition = AnimationUtils.makeInAnimation(aView.getContext(), true);
	    screenTransition.setDuration(aScreenTransitionDuration);

	    aView.startAnimation(screenTransition);
	}

	/*
	 * A transition that will show the next screen by fading it in to the current one.
	 *
	 * @param aView the view that will be involved in the transition.
	 * @param aScreenTransitionDuration the duration of the screen transition.
	 */
	static private void applyScreenTransitionFadeIn(View aView, int aScreenTransitionDuration)
	{
		if ( null == aView )
		{
			return;
		}

		Animation screenTransition = null;
		aView.clearAnimation();

	    screenTransition = AnimationUtils.loadAnimation(aView.getContext(), android.R.anim.fade_in);
	    screenTransition.setDuration(aScreenTransitionDuration);

	    aView.startAnimation(screenTransition);
	}

	/*
	 * A transition that will remove the current screen by fading it out.
	 *
	 * @param aView the view that will be involved in the transition.
	 * @param aScreenTransitionDuration the duration of the screen transition.
	 */
	static private void applyScreenTransitionFadeOut(View aView, int aScreenTransitionDuration)
	{
		if ( null == aView )
		{
			return;
		}

		Animation screenTransition = null;
		aView.clearAnimation();

	    screenTransition = AnimationUtils.loadAnimation(aView.getContext(), android.R.anim.fade_out);
	    screenTransition.setDuration(aScreenTransitionDuration);

	    aView.startAnimation(screenTransition);
	}
}
