package com.challengerliteralura.challengerliteralura.client;

import java.util.List;
import java.util.Scanner;

import com.challengerliteralura.challengerliteralura.entity.AutorEntity;
import com.challengerliteralura.challengerliteralura.entity.LivroEntity;
import com.challengerliteralura.challengerliteralura.mapper.ConverteDados;
import com.challengerliteralura.challengerliteralura.model.Resposta;
import com.challengerliteralura.challengerliteralura.repository.AutorRepository;
import com.challengerliteralura.challengerliteralura.repository.LivroRepository;
import com.challengerliteralura.challengerliteralura.service.ConsumoAPI;

public class ClienteLiteralura {

    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private LivroRepository livroRepositorio;
    private AutorRepository autorRepositorio;

    public ClienteLiteralura(LivroRepository livroRepositorio, AutorRepository autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
					Selecione a opção pelo número:
						1.- Buscar livro por titulo
						2.- Listar livros registrados
						3.- Listar autores registrados
						4.- Listar autores vivos em determinado ano
						5.- Listar livros por idioma
						0 - Sair
						""";
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLivroWeb();
                    break;
                case 2:
                    buscarLivros();
                    break;
                case 3:
                    buscarAutores();
                    break;
                case 4:
                    buscarAutoresVivo();
                    break;
                case 5:
                    buscarPorIdiomas();
                    break;
                case 0:
                    System.out.println("até logo");
                    break;
                default:
                    System.out.println("opção inválida");
            }
        }

    }

    private void buscarLivros() {

        List<LivroEntity> livros = livroRepositorio.findAll();

        if (!livros.isEmpty()) {

            for (LivroEntity livro : livros) {
                System.out.println("\n\n---------- LIVROS -------\n");
                System.out.println(" Titulo: " + livro.getTitulo());
                System.out.println(" Autor: " + livro.getAutor().getNome());
                System.out.println(" Idioma: " + livro.getLinguagem());
                System.out.println(" Download: " + livro.getDownloads());
                System.out.println("\n-------------------------\n\n");
            }

        } else {
            System.out.println("\n\n ----- NENHUM RESULTADO ENCONTRADO ---- \n\n");
        }

    }

    private void buscarAutores() {
        List<AutorEntity> autores = autorRepositorio.findAll();

        if (!autores.isEmpty()) {
            for (AutorEntity autor : autores) {
                System.out.println("\n\n---------- Autores -------\n");
                System.out.println(" Nome: " + autor.getNome());
                System.out.println(" Data de Nascimento: " + autor.getDataNascimento());
                System.out.println(" Data de Falecimento: " + autor.getDataFalescimento());
                System.out.println(" Livros: " + autor.getLivros().getTitulo());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NENHUM RESULTADO ENCONTRADO ---- \n\n");

        }

    }

    private void buscarAutoresVivo() {
        System.out.println("Escreva o ano: ");
        var ano = teclado.nextInt();
        teclado.nextLine();

        List<AutorEntity> autores = autorRepositorio.findForYear(ano);

        if (!autores.isEmpty()) {
            for (AutorEntity autor : autores) {
                System.out.println("\n\n---------- Autores Vivos -------\n");
                System.out.println(" Nome: " + autor.getNome());
                System.out.println(" Data de  nascimento: " + autor.getDataNascimento());
                System.out.println(" Data de falescimento: " + autor.getDataFalescimento());
                System.out.println(" Livros: " + autor.getLivros().getTitulo());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NENHUM RESULTADO ENCONTRADO ---- \n\n");

        }
    }

    private void buscarPorIdiomas() {

        var menu = """
				Seleccione um Idioma:
					1.- Português
					2.- Inglês
				    3.- Francês
				    4.- Polonês
				    5.- Russo
					""";
        System.out.println(menu);
        var idioma = teclado.nextInt();
        teclado.nextLine();

        String selecao = "";

        if(idioma == 1) {
            selecao = "es";
        } else 	if(idioma == 2) {
            selecao = "en";
        } else if (idioma == 3) {
            selecao = "fr";
        } else if (idioma == 4) {
            selecao = "pl";
        } else if (idioma == 5) {
            selecao = "ru";
        }

        List<LivroEntity> livros = livroRepositorio.findForLinguagem(selecao);

        if (!livros.isEmpty()) {

            for (LivroEntity livro : livros) {
                System.out.println("\n\n---------- LIVROS POR IDIOMA-------\n");
                System.out.println(" Titulo: " + livro.getTitulo());
                System.out.println(" Autor: " + livro.getAutor().getNome());
                System.out.println(" Idioma: " + livro.getLinguagem());
                System.out.println(" Downloads: " + livro.getDownloads());
                System.out.println("\n-------------------------\n\n");
            }

        } else {
            System.out.println("\n\n ----- NENHUM RESULTADO ENCONTRADO  ---- \n\n");
        }


    }

    private void buscarLivroWeb() {
        Resposta dados = getDadosSerie();

        if (!dados.results().isEmpty()) {

            LivroEntity livro = new LivroEntity(dados.results().get(0));
            livro = livroRepositorio.save(livro);

        }

        System.out.println("Dados: ");
        System.out.println(dados);
    }

    private Resposta getDadosSerie() {
        System.out.println("Insira o nome do livro que deseja buscar: ");
        var titulo = teclado.nextLine();
        titulo = titulo.replace(" ", "%20");
        System.out.println("Titlulo : " + titulo);
        System.out.println(URL_BASE + titulo);
        var json = consumoApi.obterDados(URL_BASE + titulo);
        System.out.println(json);
        Resposta datos = conversor.obterDados(json, Resposta.class);
        return datos;
    }

}

