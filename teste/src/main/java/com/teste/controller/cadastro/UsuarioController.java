package com.teste.controller.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teste.model.cadastro.Usuario;
import com.teste.service.cadastro.UsuarioService;

@Controller
@RequestMapping("cadastro/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	 @GetMapping(value = { "/listar","/listar/{numPage}"})
	    public String listar(Model model, @PathVariable(name = "numPage", required = false) Integer numPage, @RequestParam(name = "search", required = false) String search) {

	        if (numPage == null){
	            return "redirect:/cadastro/usuario/listar/1";
	        }

	        Page<Usuario> paginacao = null;

	        if (search != null && !search.equals("")){
	            model.addAttribute("search", search);
	            paginacao = usuarioService.listAll(1, search);
	        }else{
	            paginacao = usuarioService.listAll((numPage != null) ? numPage : 1);
	        }

	        model.addAttribute("paginaAtual", (numPage != null) ? numPage : 1);
	        model.addAttribute("totalPaginas", paginacao.getTotalPages());
	        model.addAttribute("totalRegistros", paginacao.getTotalElements());
	        model.addAttribute("usuarios", paginacao.getContent());

	        return "cadastro/usuario/listar";
	    }
	 
	    @GetMapping("/adicionar")
	    public String adicionar(@RequestParam("image") MultipartFile multipartFile,Model model, Usuario usuario) {
	
	        return "cadastro/usuario/adicionar";
	    }

	    @GetMapping("/editar/{id}")
	    public String editar(@RequestParam("image") MultipartFile multipartFile,@PathVariable("id") Long id, Model model) {
	        model.addAttribute("usuario", usuarioService.buscarPorId(id));
	        return "cadastro/usuario/editar";
	    }
	    
	    @PostMapping("/salvar")
	    public String salvar(@RequestParam("image") MultipartFile multipartFile,@Validated Usuario usuario, BindingResult result, RedirectAttributes attr, Model model) {

	        if (result.hasErrors()){
	            

	            if (usuario.getId() == null){
	                return "/cadastro/usuario/adicionar";
	            }else{
	                model.addAttribute("usuario", usuarioService.buscarPorId(usuario.getId()));
	                return "/cadastro/usuario/editar";
	            }
	        }

	        String destino = (usuario.getId() == null) ? "redirect:/cadastro/usuario/adicionar" : "redirect:/cadastro/usuario/editar/" + usuario.getId();

	        usuarioService.salvar(usuario);
	        attr.addFlashAttribute("sucesso", "Dados salvos com sucesso.");
	        return destino;
	    }
	    
	    @GetMapping("/excluir/{id}")
	    public String excluir(@RequestParam("image") MultipartFile multipartFile,@PathVariable("id") Long id, RedirectAttributes attr) {
	        usuarioService.excluir(id);
	        attr.addFlashAttribute("sucesso", "Dados excluídos com sucesso.");
	        return "redirect:/cadastro/usuario/listar";
	    }

	    @GetMapping("/visualizar/{id}")
	    public String visualizar(@RequestParam("image") MultipartFile multipartFile,@PathVariable("id") Long id, Model model) {
	        model.addAttribute("usuario", usuarioService.buscarPorId(id));
	        return "cadastro/usuario/visualizar";
	    }
	    
	    @ExceptionHandler(IllegalArgumentException.class)
	    public String onError(){
	        return "redirect:/home";
	    }
	    

}
