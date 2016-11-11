/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.android.phone.vvm.omtp.protocol;

import android.annotation.Nullable;
import android.content.res.Resources;
import android.telephony.TelephonyManager;

import com.android.phone.R;
import com.android.phone.vvm.omtp.VvmLog;

public class VisualVoicemailProtocolFactory {

    private static final String TAG = "VvmProtocolFactory";

    private static final String VVM_TYPE_VVM3 = "vvm_type_vvm3";

    @Nullable
    public static VisualVoicemailProtocol create(Resources resources, String type) {
        if (type == null) {
            return null;
        }
        switch (type) {
            case TelephonyManager.VVM_TYPE_OMTP:
                return new OmtpProtocol();
            case TelephonyManager.VVM_TYPE_CVVM:
                return new CvvmProtocol();
            case VVM_TYPE_VVM3:
                if (resources.getBoolean(R.bool.vvm3_enabled)) {
                    return new Vvm3Protocol();
                } else {
                    VvmLog.e(TAG, "VVM3 is disabled");
                    return null;
                }
            default:
                VvmLog.e(TAG, "Unexpected visual voicemail type: " + type);
        }
        return null;
    }
}
