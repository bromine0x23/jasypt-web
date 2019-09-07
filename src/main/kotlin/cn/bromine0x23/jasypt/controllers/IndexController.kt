package cn.bromine0x23.jasypt.controllers

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/")
class IndexController {

	@GetMapping
	fun index(
		model: Model
	): String {
		return "index"
	}

	@PostMapping("encrypt")
	fun encrypt(
		@RequestParam("input") input: String,
		@ModelAttribute("config") config: SimpleStringPBEConfig,
		redirectAttributes: RedirectAttributes
	): String {
		val output = StandardPBEStringEncryptor().apply {
			setConfig(config)
		}.encrypt(input)
		redirectAttributes.addFlashAttribute("config", config)
		redirectAttributes.addFlashAttribute("output", output)
		return "redirect:/"
	}

	@PostMapping("decrypt")
	fun decrypt(
		@RequestParam("input") input: String,
		@ModelAttribute("config") config: SimpleStringPBEConfig,
		redirectAttributes: RedirectAttributes
	): String {
		val output = StandardPBEStringEncryptor().apply {
			setConfig(config)
		}.decrypt(input)
		redirectAttributes.addFlashAttribute("config", config)
		redirectAttributes.addFlashAttribute("output", output)
		return "redirect:/"
	}
}