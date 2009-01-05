/*
 * Copyright (c) 2008-2009 by Xuggle Inc. All rights reserved.
 *
 * It is REQUESTED BUT NOT REQUIRED if you use this library, that you let 
 * us know by sending e-mail to info@xuggle.com telling us briefly how you're
 * using the library and what you like or don't like about it.
 *
 * This library is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 2.1 of the License, or (at your option) any later
 * version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
/**
 * The Xuggle "Thread Lifecycle Manager" (TLM).  A set of utilities for managing the life-cycle of a Java thread.
 * To create an object that the TLM can managed, first implement:
 * {@link com.xuggle.utils.tlm.IThreadLifecycleManagedRunnable}
 * then pass your implementation to a :
 * {@link com.xuggle.utils.tlm.ThreadLifecycleManager}
 * and call {@link com.xuggle.utils.tlm.ThreadLifecycleManager#start()}.
 * <p>
 * This package implements the common pattern of wanting to
 * start a thread, but having some way of letting initialization
 * happen before other threads start handing off requests.
 * </p>
 * <p>
 * It does this by implementing a simple state machine for the
 * managed object that transitions as follows (in the sunny-day
 * case of a thread starting and stopping cleanly):
 * </p><p>
 * <ol>
 *  <li>{@link com.xuggle.utils.tlm.IThreadLifecycleManager#STOPPED} transitions to</li>
 *  <li>{@link com.xuggle.utils.tlm.IThreadLifecycleManager#STARTING} transitions to</li>
 *  <li>{@link com.xuggle.utils.tlm.IThreadLifecycleManager#STARTED} transitions to</li>
 *  <li>{@link com.xuggle.utils.tlm.IThreadLifecycleManager#STOPPING} transitions to</li>
 *  <li>{@link com.xuggle.utils.tlm.IThreadLifecycleManager#STOPPED} transitions to</li>
 * </ol>
 * In that case, each state is transitioned through.
 * </p><p>
 * During state transitions, the following methods are called:
 * </p>
 * <ul>
 * <li>{@link com.xuggle.utils.tlm.IThreadLifecycleManagedRunnable#initialize(IThreadLifecycleManager)} methods are
 * called while in the {@link com.xuggle.utils.tlm.IThreadLifecycleManager#STARTING} state.</li>
 * <li>
 * {@link com.xuggle.utils.tlm.IThreadLifecycleManagedRunnable#execute(IThreadLifecycleManager)} methods are called while in
 * the {@link com.xuggle.utils.tlm.IThreadLifecycleManager#STARTED} state.
 * </li>
 * <li>
 * {@link com.xuggle.utils.tlm.IThreadLifecycleManagedRunnable#finish(IThreadLifecycleManager, Throwable)} methods are called while
 * in the {@link com.xuggle.utils.tlm.IThreadLifecycleManager#STOPPING} state.
 * </li>
 * </ul>
 * <p>
 * Interested parties can register {@link com.xuggle.utils.event.IEventHandler}s on
 * the {@link com.xuggle.utils.tlm.ThreadLifecycleManager} for
 * {@link com.xuggle.utils.sm.StateMachine.TransitionEvent}s
 * to see when state transitions occur.
 * </p><p>
 * Less interested parties can just register {@link com.xuggle.utils.event.IEventHandler}s
 * on the {@link com.xuggle.utils.tlm.ThreadLifecycleManager}
 * for the
 * {@link com.xuggle.utils.tlm.IThreadLifecycleManager.RunnableStarted}
 * or
 * {@link com.xuggle.utils.tlm.IThreadLifecycleManager.RunnableStopped}
 * events.
 * </p>
 */
package com.xuggle.utils.tlm;

