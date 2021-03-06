/*
 * Copyright 2014 wada811
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.gdut.xietong.supervisionsystem.dialog.interfaces;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public interface DialogFragmentInterface {

    String getTag();

    void show(FragmentManager manager, String tag);

    int show(FragmentTransaction transaction, String tag);

    Dialog getDialog();

    DialogFragmentInterface setExtra(Bundle extra);

    Bundle getExtra();

}
