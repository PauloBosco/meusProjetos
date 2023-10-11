using APICatalogo.DTOs;
using APICatalogo.Models;
using APICatalogo.Pagination;
using APICatalogo.Repository;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace APICatalogo.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class ProdutosController : ControllerBase
    {
        private readonly IUnityOfWork _uof;
        private readonly IMapper _mapper;

        public ProdutosController(IUnityOfWork context, IMapper mapper)
        {
            _uof = context;
            _mapper = mapper;
        }

        [HttpGet("menorpreco")]
        public async Task<ActionResult<IEnumerable<ProdutoDTO>>> GetProdutosPrecos()
        {
            var produtos = await _uof.produtoRepository.GetProdutosPorPreco();
            var produtosDTO = _mapper.Map<List<ProdutoDTO>>(produtos);
            return produtosDTO;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<ProdutoDTO>>> Get([FromQuery]ProdutosParameters produtosParameters)
        {
            
            var produtos = await _uof.produtoRepository.GetProdutos(produtosParameters);
            var produtosDTO = _mapper.Map<List<ProdutoDTO>>(produtos);
            if(produtos is null)
            {
                return NotFound("Produtos não encontrados...");
            }

            var metaData = new
            {
                produtos.TotalCount,
                produtos.PageSize,
                produtos.CurrentPage,
                produtos.TotalPages,
                produtos.HasNext,
                produtos.HasPrevious
            };

            Response.Headers.Add("X-Pagination", JsonConvert.SerializeObject(metaData));

            return produtosDTO;
        }

        [HttpGet("{id:int}", Name = "ObterProduto")]
        public async Task<ActionResult<ProdutoDTO>> Get(int id)
        {
            var produto = await _uof.produtoRepository.GetById(p => p.ProdutoId == id);
            if(produto is null)
            {
                return NotFound("Produto não encontrado...");
            }
            var produtoDTO = _mapper.Map<ProdutoDTO>(produto);
            return produtoDTO;
        }

        [HttpPost]
        public async Task<ActionResult> Post([FromBody] ProdutoDTO produtoDto)
        {
            var produto = _mapper.Map<Produto>(produtoDto);
            
            if(produto is null)
            {
                return BadRequest();
            }
            _uof.produtoRepository.Add(produto);
            await _uof.Commit();

            var produtoDTO = _mapper.Map<ProdutoDTO>(produto);

            return new CreatedAtRouteResult("ObterProduto", 
                new { id = produto.ProdutoId }, produtoDTO);
        }

        [HttpPut("{id:int}")]
        public async Task<ActionResult> Put(int id, ProdutoDTO produtoDto)
        {
            if(id != produtoDto.ProdutoId)
            {
                return BadRequest();
            }

            var produto = _mapper.Map<Produto>(produtoDto);

            _uof.produtoRepository.Update(produto);
            await _uof.Commit();

            return Ok(produto);
        }

        [HttpDelete("{id:int}")]
        public async Task<ActionResult<ProdutoDTO>> Delete(int id)
        {
            var produto = await _uof.produtoRepository.GetById(p => p.ProdutoId == id);
            if(produto is null)
            {
                return NotFound("Produto não encontrado");
            }

            _uof.produtoRepository.Delete(produto);
            await _uof.Commit();

            var produtoDto = _mapper.Map<ProdutoDTO>(produto);

            return produtoDto;
        }
    }
}
