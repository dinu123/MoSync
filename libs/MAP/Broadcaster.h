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
* \file Broadcaster.h
* \brief Typed notification broadcasting to multiple listeners
* \author Lars-Åke Vinberg
*/

#ifndef BROADCASTER_H_
#define BROADCASTER_H_

#include <MAUtil/Vector.h>



namespace MAPUtil
{

	using namespace MAUtil;

	//=========================================================================
	/**
	 * \brief This template class enables typed notification broadcasting to multiple
	 * listeners.
	 * 
	 * Listeners must implement class T.
	 * 
	 * A listener adds itself to the list of listeners by calling AddListener( ) on
	 * the object it wants to listen to.
	 * 
	 * To broadcast, loop through all listeners in mListeners and call the appropriate
	 * notification function.
	 *
	 * Example:
	 *
	 *    ...
	 *    for ( int i = 0; i < mListeners.size( ); i++ )
	 *       m_Listeners[i]->notify( );
	 * 
	 */
	template<class T>
	class Broadcaster
	//=========================================================================
	{
	public:
		/**
		* Add a listener.
		* Clients should call this to register for notification.
		*/
		void addListener( T* listener ) 
		{
			mListeners.add( listener );
		}
		/**
		 * Remove a listener.
		 * Clients should call this to unregister for notification.
		 */
		void removeListener( T* listener ) 
		{ 
			for ( int i = 0; i < mListeners.size( ); i++ ) 
			{
				if ( mListeners[i] == listener )
				{
					mListeners.remove( i );
					return;
				}
			}
		}

		Vector<T*>* getListeners( ) { return &mListeners; }
	protected:

	private:
		/**
		 * The list of listeners.
		 * Implementer should iterate through list and call the appropriate
		 * notify function.
		 */
		Vector<T*> mListeners;
	};

	//
	// This static template function is necessary to avoid ambiguity in multiple inheritance of broadcaster:
	//
	//	class BaseListener;
	//	class SubListener;
	//
	//	class Base : public Broadcaster<BaseListener>
	//	{
	//		...
	//	}
	//
	//	class Sub : public Base, public Broadcaster<SubListener>
	//	{
	//		...
	//	}
	//
	//	The abmiguity that (some) c++ compilers cannot resolve is in getListeners (see above).
	//	implementing getListeners as template just won't work.
	//		

	template<class T> Vector<T*>* getBroadcasterListeners( Broadcaster<T>& broadcaster ) { return broadcaster.getListeners( ); }

}

#endif // BROADCASTER_H_
