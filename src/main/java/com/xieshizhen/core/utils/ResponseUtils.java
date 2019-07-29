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
package com.xieshizhen.core.utils;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/29
 * unified response class
 */
public class ResponseUtils {
    private Integer status = 200;
    private Object message;
    private Object data;
    private static ResponseUtils utils = null;

    public <T, S> ResponseUtils success(T data, S message) {
        utils.setData(data);
        utils.setStatus(200);
        utils.setMessage(message);
        return utils;
    }

    /**
     * overloaded success method
     *
     * @param status
     * @param message
     * @param <S>
     * @return
     */
    public <S> ResponseUtils success(Integer status, S message) {
        utils.setMessage(message);
        utils.setData(null);
        utils.setStatus(status);
        return utils;
    }

    /**
     * overloaded success method
     *
     * @param data
     * @param <T>
     * @return
     */
    public <T> ResponseUtils success(T data) {
        utils.setMessage(null);
        utils.setData(data);
        utils.setStatus(200);
        return utils;
    }

    public <S> ResponseUtils error(Integer status, S message) {
        utils.setData(null);
        utils.setStatus(status);
        utils.setMessage(message);
        return utils;
    }

    /**
     * The singleton pattern obtains the object of the reply layer
     *
     * @return
     */
    public static ResponseUtils getInstance() {
        if (utils == null) {
            utils = new ResponseUtils();
        }
        return utils;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
