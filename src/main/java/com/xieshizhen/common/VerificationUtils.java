/**
 * Copyright © 2019 XieShiZhen(Kitetop). All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xieshizhen.common;

import org.springframework.validation.BindingResult;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/08/29
 * Verify data
 */
public class VerificationUtils {
    private static VerificationUtils valid = null;

    private VerificationUtils() {
    }

    /**
     * If a data add a @Valid target, use this method can validate it
     * @param result
     * @throws ExceptionUtils
     */
    public void verifyBind(BindingResult result) throws ExceptionUtils {
        if(result.hasErrors()) {
            String message = result.getFieldError().getDefaultMessage();
            throw new ExceptionUtils(400, message);
        }
    }

    public static VerificationUtils getInstance() {
        if(valid == null) {
            valid = new VerificationUtils();
        }
        return valid;
    }

}
