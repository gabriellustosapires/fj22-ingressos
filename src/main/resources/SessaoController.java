import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.form.SessaoForm;

@Controller
public class SessaoController {
	@Autowride
	private SalaDao salaDao;
	
	@Autowride 
	private FilmeDao FilmeDao;
	
	@Autowride
	private SessaoDao sessaoDao;
	
	@PostMapping (value = "/admin/sessao")
	@Transactional
	public ModelAndView salva(@Valid SessaoForm form, BindingResult result){
		if(result.hasErrors() return form(form.getSalaId(),form);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/sala/"+form.getSalaId()+"/sessoes");
		
		Sessao sessao = form.toSessao(salaDao, filmeDao);
		
		sessaoDao.save(sessao);
		
		return modelAndView;
	}
	
	
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId")Integer salaId, SessaoForm form){
		
		form.setSalaId(salaId);
		
		ModelAndView modelAndView = new ModelAndView ("sessao/sessao");
		
		modelAndView.addObject("sala", salaDao.findOne(salaId));
		modelAndView.addObject("filmes", FilmeDao.findAll())
		modelAndView.addObject("form", form);
	}
	
	
}
