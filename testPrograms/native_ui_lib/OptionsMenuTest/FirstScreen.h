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

/**
 * @file FirstScreen.h.
 * @author emma
 */

#ifndef FIRSTSCREEN_H_
#define FIRSTSCREEN_H_

#include <maapi.h>
#include <MAUtil/util.h>

// Include all the wrappers.
#include <NativeUI/Widgets.h>

using namespace NativeUI;

class FirstScreen:
	public Screen,
	public ScreenListener,
	public ButtonListener
{

public:
	/**
	 * Constructor.
	 */
	FirstScreen();

	/**
	 * Destructor.
	 */
	~FirstScreen();

    /**
     * This method is called when the OptionsMenu is being closed
     * (either by the user canceling the menu with the back/menu
     * button, or when an item is selected.
     * @param Screen The screen that generated the event.
     */
    virtual void optionsMenuClosed(Screen* screen);

    /**
     * This method is called when an options menu item is selected.
     * @param index The index on which the item is placed into the
     * OptionsMenu.
     *  @param Screen The screen that generated the event.
     */
    virtual void optionsMenuItemSelected(Screen* screen, int index);

    /**
     * This method is called if the touch-up event was inside the
     * bounds of the button.
     * Platform: iOS and Android.
     * @param button The button object that generated the event.
     */
    virtual void buttonClicked(Widget* button);
private:
	/**
	 * Main layout.
	 */
	VerticalLayout* mMainLayout;
	ListView* mEventsList;
	Button* mClearOptions;
};

#endif /* FIRSTSCREEN_H_ */
