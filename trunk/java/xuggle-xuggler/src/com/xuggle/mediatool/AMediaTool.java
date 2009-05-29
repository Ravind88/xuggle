/*
 * Copyright (c) 2008, 2009 by Xuggle Incorporated.  All rights reserved.
 * 
 * This file is part of Xuggler.
 * 
 * You can redistribute Xuggler and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Xuggler is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public
 * License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with Xuggler.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.xuggle.mediatool;


import com.xuggle.xuggler.IContainer;

/**
 * Internal Only. An abstract base class for implementing {@link IMediaPipe}
 * objects used internally by the mediatool package.
 * 
 * @author trebor
 */

public abstract class AMediaTool extends AMediaPipe
{
  // the container to read from or write to
  
  protected final IContainer mContainer;

  // true if this media writer should close the container

  protected boolean mCloseContainer;

  // the URL which is read or written

  private final String mUrl;

  // all the media reader listeners

  /**
   * Construct an abstract IMediaPipe.
   *
   * @param url the URL which will be read or written to
   * @param container the container which be read from or written to
   */
  
  public AMediaTool(String url, IContainer container)
  {
    mUrl = url;
    mContainer = container.copyReference();

    // it is assuemd that the container should not be closed by the
    // tool, this may change if open() is laster called 

    mCloseContainer = false;
  }

  /** {@inheritDoc} */

  public String getUrl()
  {
    return mUrl;
  }

  /** {@inheritDoc} */

  public IContainer getContainer()
  {
    return mContainer == null ? null : mContainer.copyReference();
  }

  /** {@inheritDoc} */
  
  public boolean isOpen()
  {
    return mContainer.isOpened();
  }
}