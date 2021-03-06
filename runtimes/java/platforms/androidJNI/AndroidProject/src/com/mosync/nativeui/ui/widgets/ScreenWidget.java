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

package com.mosync.nativeui.ui.widgets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import com.mosync.internal.android.MoSyncThread;
import com.mosync.internal.generated.IX_WIDGET;
import com.mosync.nativeui.core.NativeUI;
import com.mosync.nativeui.util.properties.IntConverter;
import com.mosync.nativeui.util.properties.InvalidPropertyValueException;
import com.mosync.nativeui.util.properties.PropertyConversionException;

/**
 * A screen widget that represents the root of a widget
 * tree.
 *
 * Note: For now a screen widget is just a wrapper to a view
 * like all other widgets, but by default it fills the whole
 * screen.
 *
 * @author fmattias
 */
public class ScreenWidget extends Layout
{
	/**
	 * Title of this screen.
	 */
	private String m_title = "";

	/**
	 * Icon of this screen.
	 */
	private Drawable m_icon = null;

	/**
	 * Listener for when the title changes.
	 */
	private TitleChangedListener m_titleChangedListener = null;

	/**
	 * Listener for when the icon changes.
	 */
	private IconChangedListener m_iconChangedListener = null;

	/**
	 * The menu items.
	 * Used for the OptionsMenu on older devices, or the ActionBar for newer ones.
	 * Queried when the options menu button or an ActionBar item were pressed.
	 */
	private List<OptionsMenuItem> m_optionsItems = new ArrayList<OptionsMenuItem>();

	/**
	 * Constructor
	 *
	 * @param handle handle Integer handle corresponding to this instance.
	 * @param view A screen wrapped by this widget.
	 */
	public ScreenWidget(int handle, ViewGroup view)
	{
		super( handle, view );
	}

	/**
	 * Add an options menu item.
	 * @param title The title for this item.
	 * @param icon The icon resource id.
	 * @return The new item index.
	 */
	public int addOptionsMenuItem(final String title, int iconId)
	{
		OptionsMenuItem item = new OptionsMenuItem(m_optionsItems.size(), title, iconId);
		m_optionsItems.add(item);
		return m_optionsItems.size()-1;
	}

	/**
	 * Add an options menu item.
	 * @param title The title for this item.
	 * @param icon The icon drawable.
	 * @return The new item index.
	 */
	public int addOptionsMenuItem(final String title, Drawable icon)
	{
		OptionsMenuItem item = new OptionsMenuItem(m_optionsItems.size(), title, icon);
		m_optionsItems.add(item);
		return m_optionsItems.size()-1;
	}

	/**
	 * Add a menu item for the ActionBar.
	 * @param title The title for this item.
	 * @param icon The icon drawable.
	 * @param flag Sets how this item should display in the presence of an Action Bar.
	 * @return The new item handle.
	 */
	public int addActionBarMenuItem(final String title, Drawable icon, int flag)
	{
		int itemHandle = MoSyncThread.getInstance().nativeCreatePlaceholder();
		OptionsMenuItem item = new OptionsMenuItem(itemHandle, title, icon);
		item.setShowAsAction(flag);
		m_optionsItems.add(item);
		return itemHandle;
	}

	/**
	 * Add a menu item for the ActionBar.
	 * @param title The title for this item.
	 * @param icon The icon resource id.
	 * @param flag Sets how this item should display in the presence of an Action Bar.
	 * @return The new item handle.
	 */
	public int addActionBarMenuItem(final String title, int iconId, int flag)
	{
		int itemHandle = MoSyncThread.getInstance().nativeCreatePlaceholder();
		OptionsMenuItem item = new OptionsMenuItem(itemHandle, title, iconId);
		item.setShowAsAction(flag);
		m_optionsItems.add(item);
		return itemHandle;
	}

	public int removeActionBarMenuItem(int itemHandle)
	{
		Iterator<OptionsMenuItem> itemsIt = m_optionsItems.iterator();
		int itemIndex=0;
		while (itemsIt.hasNext())
		{
			if ( itemsIt.next().getId() == itemHandle )
			{
				m_optionsItems.remove(itemIndex);
				return IX_WIDGET.MAW_RES_OK;
			}
			itemIndex++;
		}

		return -1;
	}

	public List<OptionsMenuItem> getMenuItems()
	{
		return m_optionsItems;
	}

	@Override
	public boolean setProperty(String property, String value)
			throws PropertyConversionException, InvalidPropertyValueException
	{
		if( super.setProperty( property, value ) )
		{
			return true;
		}

		if( property.equals( IX_WIDGET.MAW_SCREEN_TITLE ) )
		{
			m_title = value;
			if( m_titleChangedListener != null )
			{
				m_titleChangedListener.titleChanged( this, m_title );
			}
		}
		else if( property.equals( IX_WIDGET.MAW_SCREEN_ICON ) )
		{
			int imageHandle = IntConverter.convert( value );
			Bitmap icon = NativeUI.getBitmap( imageHandle );
			if( icon != null )
			{
				m_icon = new BitmapDrawable( NativeUI.getBitmap( imageHandle ) );
				if ( m_iconChangedListener != null )
				{
					m_iconChangedListener.iconChanged( this, m_icon);
				}
			}
			else
			{
				throw new InvalidPropertyValueException( value, property );
			}
		}
		else if( property.equals( IX_WIDGET.MAW_SCREEN_REMOVE_OPTIONS_MENU ) )
		{
			m_optionsItems.clear();
		}
		else
		{
			return false;
		}

		return true;
	}

	/**
	 * @see Widget.getProperty.
	 */
	@Override
	public String getProperty(String property)
	{
		if( property.equals( IX_WIDGET.MAW_SCREEN_IS_SHOWN ))
		{
			return Boolean.toString( this.isShown() );
		}
		else
		{
			return super.getProperty( property );
		}
	}

	/**
	 * @see Layout.updateLayoutParams.
	 */
	@Override
	public ViewGroup.LayoutParams createNativeLayoutParams(LayoutParams mosyncLayoutParams)
	{
		return new android.widget.FrameLayout.LayoutParams( mosyncLayoutParams.getWidth( ) , mosyncLayoutParams.getHeight( ) );
	}

	/**
	 * Returns the title of this screen.
	 *
	 * @return the title of this screen.
	 */
	public String getTitle()
	{
		return m_title;
	}

	/**
	 * Returns the icon of this screen.
	 *
	 * @return the icon of this screen.
	 */
	public Drawable getIcon()
	{
		return m_icon;
	}

	/**
	 * Sets a listener that is called when the title
	 * of the screen has changed.
	 *
	 * @param titleChangedListener A listener for title changes.
	 */
	public void setTitleChangedListener(TitleChangedListener titleChangedListener)
	{
		m_titleChangedListener = titleChangedListener;
	}

	public void setIconChangedListener(IconChangedListener iconChangedListener)
	{
		m_iconChangedListener = iconChangedListener;
	}

	/**
	 * Check if this screen is shown.
	 * @return true if the screen is displayed, false otherwise.
	 */
	public boolean isShown()
	{
		ScreenWidget currentScreen = MoSyncThread.getInstance().getCurrentScreen();
		return this.equals( currentScreen );
	}

	/**
	 * A listener class for when the title of a screen changes,
	 * so that the for example the tab screen has a chance
	 * to update its tab title.
	 *
	 * @author fmattias
	 */
	public interface TitleChangedListener
	{
		/**
		 * Called when a title of the screen has changed.
		 *
		 * @param screen The screen whose title has changed.
		 * @param newTitle The new title of the screen.
		 */
		void titleChanged(ScreenWidget screen, String newTitle);
	}

	/**
	 * A listener class for when the icon of a screen changes,
	 * so that the for example the tab screen has a chance
	 * to update its icon in the tab indicator.
	 *
	 * @author emma
	 */
	public interface IconChangedListener
	{
		/**
		 * Called when a icon of the screen has changed.
		 *
		 * @param screen The screen whose icon has changed.
		 * @param newIcon The new icon of the screen.
		 */
		void iconChanged(ScreenWidget screen, Drawable newIcon);
	}
}