namespace APICatalogo.Repository;

public interface IUnityOfWork
{
    IProdutoRepository produtoRepository { get; }
    ICategoriaRepository categoriaRepository { get; }
    Task Commit(); 
}
