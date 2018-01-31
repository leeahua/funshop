package com.next.funshop.utils;

import com.next.funshop.enums.ResultEnum;
import com.next.funshop.vo.ResultVO;

/**
 * 描述：〈响应信息封装〉
 *
 * @author liyaohua
 * @create 2018/1/31
 * @since 1.0.0
 */
public class ResultVOUtil {

    /**
     * @Description: <br>
     * 有返回值成功响应
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/31 下午4:33
     */

    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(Integer.valueOf(ResultEnum.SUCCESS.getCode()));
        resultVO.setMsg(ResultEnum.SUCCESS.getMessage());
        resultVO.setData(data);
        return resultVO;
    }
    /**
     * @Description: <br>
     * 无返回值成功响应
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/31 下午4:55
     */
    public static ResultVO success(){
        return success(null);
    }
    /**
     * @Description: <br>
     *  失败响应信息
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/31 下午4:55
     */
    public static ResultVO failed(ResultEnum resultEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(Integer.valueOf(resultEnum.getCode()));
        resultVO.setMsg(resultEnum.getMessage());
        resultVO.setData(null);
        return resultVO;
    }
}
