package gestao.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import gestao.models.hospital.Hospital;
import gestao.models.produto.Produto;
import gestao.respositories.ProdutoRepository;
import gestao.respositories.hospital.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ProdutoServiceImpl {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> findAll(Long idHospital) {
        Hospital h = hospitalRepository.findById(idHospital).get();
        return Collections.unmodifiableList(h.getProdutos());
    }

    public Optional<Produto> find(Long idHospital, String nome) {
        Hospital h = hospitalRepository.findById(idHospital).get();
        return h.getProdutos().stream().filter(p -> p.getNome().equals(nome)).findFirst();
    }

    /*
    public Produto create(Produto p){
        return this.produtoRepository.save(p);
    }
     */
    public boolean add(Long idHospital, @Valid Produto produto, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return false;
        }
        Hospital h = hospitalRepository.findById(idHospital).get();
        h.addProduto(produto);
        hospitalRepository.save(h);
        return true;
    }

    public boolean update(Long idHospital, Long id, @Valid Produto produto, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return false;
        }
        Hospital h = hospitalRepository.findById(idHospital).get();
        Produto p = h.getProdutos().stream().filter(pr -> pr.equals(produto)).findFirst().get();
        p.setNome(produto.getNome());
        p.setDescricao(produto.getDescricao());
        p.setQuantidade(produto.getQuantidade());
        hospitalRepository.save(h);
        //produtoRepository.save(p);
        return true;
    }

    public boolean delete(Long idHospital, Long id) {
        Hospital h = hospitalRepository.findById(idHospital).get();
        Optional<Produto> p = h.getProdutos().stream().filter(pr -> pr.getId().equals(id)).findFirst();
         
        if (p.isPresent()) {
            h.getProdutos().remove(p.get());
             hospitalRepository.save(h);
            return true;
        }
        return false;
    }

}
