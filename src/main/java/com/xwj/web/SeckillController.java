package com.xwj.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xwj.entity.Seckill;
import com.xwj.enums.SeckillStatEnum;
import com.xwj.exception.RepeatKillException;
import com.xwj.exception.SeckillCloseException;
import com.xwj.service.SeckillService;
import com.xwj.vo.Exposer;
import com.xwj.vo.SeckillExcution;
import com.xwj.vo.SeckillResult;

/**
 * 秒杀控制层
 * 
 * @author xuwenjin
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillService seckillService;

	/**
	 * 获取列表页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		logger.info("list={}", list);
		return "list";
	}

	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
		if (seckillId == null) {
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if (seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}

	// ajax ,json暴露秒杀接口的方法
	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
		SeckillResult<Exposer> result;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result = new SeckillResult<Exposer>(false, e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<SeckillExcution> execute(@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5, @CookieValue(value = "userPhone", required = false) String userPhone) {
		if (userPhone == null) {
			return new SeckillResult<SeckillExcution>(false, "未注册");
		}
		try {
//			SeckillExcution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
			SeckillExcution execution = seckillService.executeSeckillProcedure(seckillId, userPhone, md5);
			return new SeckillResult<SeckillExcution>(true, execution);
		} catch (RepeatKillException e1) {
			SeckillExcution execution = new SeckillExcution(seckillId, SeckillStatEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExcution>(true, execution);
		} catch (SeckillCloseException e2) {
			SeckillExcution execution = new SeckillExcution(seckillId, SeckillStatEnum.END);
			return new SeckillResult<SeckillExcution>(true, execution);
		} catch (Exception e) {
			SeckillExcution execution = new SeckillExcution(seckillId, SeckillStatEnum.INNER_ERROR);
			return new SeckillResult<SeckillExcution>(true, execution);
		}
	}

	/**
	 * 获取系统时间
	 * @return
	 */
	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> time() {
		Date now = new Date();
		return new SeckillResult<Long>(true, now.getTime());
	}

}
