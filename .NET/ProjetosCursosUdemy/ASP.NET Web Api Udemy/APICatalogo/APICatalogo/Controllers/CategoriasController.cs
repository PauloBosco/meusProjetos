using APICatalogo.DTOs;
using APICatalogo.Models;
using APICatalogo.Pagination;
using APICatalogo.Repository;
using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace APICatalogo.Controllers
{
    //[Authorize(AuthenticationSchemes = "Bearer")]
    [Route("[controller]")]
    [ApiController]
    public class CategoriasController : ControllerBase
    {
        private readonly IUnityOfWork _uof;
        private readonly IMapper _mapper;

        public CategoriasController(IUnityOfWork context, IMapper mapper)
        {
            _uof = context;
            _mapper = mapper;
        }

        [HttpGet("produtos")]
        public async Task<ActionResult<IEnumerable<CategoriaDTO>>> GetCategoriaProdutos()
        {
            try
            {
                var categorias = await _uof.categoriaRepository.GetCategoriaProdutos();
                var categoriasDto = _mapper.Map<List<CategoriaDTO>>(categorias);
                return categoriasDto;
            }
            catch (Exception)
            {
                return StatusCode(StatusCodes.Status500InternalServerError,
                    "Ocorreu um problema ao tratar a sua solicitação.");
            }
            
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<CategoriaDTO>>> Get([FromQuery] CategoriasParameters categoriasParameters)
        {
            
            var categorias = await _uof.categoriaRepository.GetCategorias(categoriasParameters);

            var metaData = new
            {
                categorias.TotalCount,
                categorias.PageSize,
                categorias.CurrentPage,
                categorias.TotalPages,
                categorias.HasNext,
                categorias.HasPrevious
            };

            Response.Headers.Add("X-Pagination", JsonConvert.SerializeObject(metaData));

            var categoriasDto = _mapper.Map<List<CategoriaDTO>>(categorias);
            return categoriasDto;
           
        }

        [HttpGet("{id:int}", Name = "ObterCategoria")]
        public async Task<ActionResult<CategoriaDTO>> Get(int id)
        {
            var categoria = await _uof.categoriaRepository.GetById(c => c.CategoriaId == id);
            if (categoria is null)
            {
                return NotFound("Categoria não encontrado...");
            }

            var categoriasDto = _mapper.Map<CategoriaDTO>(categoria);
            return categoriasDto;
        }

        [HttpPost]
        public async Task<ActionResult> Post([FromBody]CategoriaDTO categoriaDto)
        {

            var categoria = _mapper.Map<Categoria>(categoriaDto);

            _uof.categoriaRepository.Add(categoria);
            await _uof.Commit();

            var categoriaDTO = _mapper.Map<CategoriaDTO>(categoria);

            return new CreatedAtRouteResult("ObterCategoria",
                new { id = categoria.CategoriaId }, categoriaDTO);
        }


        [HttpPut("{id:int}")]
        public async  Task<ActionResult> Put(int id, CategoriaDTO categoriaDto)
        {
            if (id != categoriaDto.CategoriaId)
            {
                return BadRequest();
            }

            var categoria = _mapper.Map<Categoria>(categoriaDto);

            _uof.categoriaRepository.Update(categoria);
            await _uof.Commit();

            return Ok(categoria);
        }

        [HttpDelete("{id:int}")]
        public async Task<ActionResult<CategoriaDTO>> Delete(int id)
        {
            var categoria = await _uof.categoriaRepository.GetById(c => c.CategoriaId == id);
            if (categoria == null)
            {
                return NotFound("Categoria não encontrada");
            }

            _uof.categoriaRepository.Delete(categoria);
            await _uof.Commit();

            var categoriaDto = _mapper.Map<CategoriaDTO>(categoria);

            return categoriaDto;
        }
    }
}
