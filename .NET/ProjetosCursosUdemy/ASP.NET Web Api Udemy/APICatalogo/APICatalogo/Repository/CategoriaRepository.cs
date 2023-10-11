using APICatalogo.Context;
using APICatalogo.Models;
using APICatalogo.Pagination;
using Microsoft.EntityFrameworkCore;

namespace APICatalogo.Repository
{
    public class CategoriaRepository : Repository<Categoria>, ICategoriaRepository
    {
        public CategoriaRepository(AppDbContext contexto) : base(contexto)
        {
        }

        public async Task<PagedList<Categoria>> GetCategorias(CategoriasParameters categoriasParameters)
        {
            return await PagedList<Categoria>.ToPagedList(Get().OrderBy(on => on.Nome),
                                                categoriasParameters.PageNumber, 
                                                categoriasParameters.PageSize);
        }
        public async Task<IEnumerable<Categoria>> GetCategoriaProdutos()
        {
            return await Get().Include(x => x.Produtos).ToListAsync();
        }

    }
}
