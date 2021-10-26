package com.eschantal.emdias.service;

import com.eschantal.emdias.domain.*;
import com.eschantal.emdias.repository.*;
import com.eschantal.emdias.service.impl.MovementBankServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovementBankServiceImplTest {

    MovementBankService movementBankService ;

    @Mock
    protected MovementBankRepository movementBankRepositoryMock;

    @Mock
    protected NaturePlanRepository naturePlanRepositoryMock;

    @Mock
    protected ProjetoNaturezaRepository projetoNaturezaRepositoryMock;

    @Mock
    protected ProjetoRepository projetoRepositoryMock;

    @Mock
        protected AccountBankRepository accountBankRepositoryMock;

    NaturezaResumo naturezaResumo ;

    @Before
    public void inicializar() {
        MockitoAnnotations.initMocks(this);
        this.movementBankService = new MovementBankServiceImpl(movementBankRepositoryMock,naturePlanRepositoryMock,projetoNaturezaRepositoryMock,projetoRepositoryMock, accountBankRepositoryMock);
    }

    @Test
    public void deveRetornarResumoSemErroNulo() {
        List<NaturezaResumo> naturezaResumo = this.movementBankService.pegaMovimentosDaNatureza(null,null);
        Assert.assertNotNull(naturezaResumo);
        //Assert.assertEquals(0,naturezaResumo.getValorTotal(),0.001);
    }
    public NaturePlan umNaturePlan(){
        NaturePlan naturePlan = new NaturePlan();
        naturePlan.setId(1L);
        naturePlan
            .descNaturePlan("NATUREZA PADRAO")
            .typeNaturePlan(TypeNaturePlan.D);
        return naturePlan;
    }
    @Test
    public void deveRetornarNaturezaInformada() {
        NaturePlan naturePlan = this.umNaturePlan();
        List<NaturezaResumo> naturezaResumo = this.movementBankService.pegaMovimentosDaNatureza(naturePlan,null);
        Assert.assertEquals(naturezaResumo.get(0).getNaturePlan(),naturePlan);
    }
    @Test
    public void filtraNaturezaNuloDeveRetornarFalse() {
        Assert.assertFalse(this.movementBankService.filtraNaturezaResumo(null));
    }
    @Test
    public void filtraNaturezaZeroSemProjetoDeveRetornarFalse() {
        this.naturezaResumo = new NaturezaResumo();
        naturezaResumo.setValorTotal(0);
        Assert.assertFalse(this.movementBankService.filtraNaturezaResumo(this.naturezaResumo));
    }
    @Test
    public void filtraNaturezaValorSemProjetoDeveRetornarTrue() {
        this.naturezaResumo = new NaturezaResumo();
        naturezaResumo.setValorTotal(34);
        Assert.assertTrue(this.movementBankService.filtraNaturezaResumo(this.naturezaResumo));
    }
    @Test
    public void filtraNaturezaZeroComValorProjetoComValorDeveRetornarTrue() {
        this.naturezaResumo = new NaturezaResumo();
        naturezaResumo.setValorTotal(0);
        ProjetoNatureza projetoNatureza = new ProjetoNatureza();
        projetoNatureza.valorPrevisto(new Double(99));
        this.naturezaResumo.setProjetoNatureza(projetoNatureza);
        Assert.assertTrue(this.movementBankService.filtraNaturezaResumo(this.naturezaResumo));
    }
    @Test
    public void filtraNaturezaZeroComValorProjetoZeroDeveRetornarFalse() {
        this.naturezaResumo = new NaturezaResumo();
        naturezaResumo.setValorTotal(0);
        ProjetoNatureza projetoNatureza = new ProjetoNatureza();
        projetoNatureza.valorPrevisto(new Double(0));
        this.naturezaResumo.setProjetoNatureza(projetoNatureza);
        Assert.assertFalse(this.movementBankService.filtraNaturezaResumo(this.naturezaResumo));
    }

    @Test
    public void atualizaProjetoNaturezaPeloNaturezaResumoParametrosNulos() {

        try {
            this.movementBankService.atualizaProjetoNaturezaPeloNaturezaResumo(null, null);
            verifyNoInteractions(this.projetoNaturezaRepositoryMock);
        } catch (Exception e) {

            Assert.fail("Não deveria lançar exessão =>" + e.getMessage());

        }
    }
    public Projeto umProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNomeProjeto("adldldld");
        return projeto ;
    }
    @Test
    public void atualizaProjetoNaturezaPeloNaturezaResumoParametroProjeto() {
        Projeto projeto = this.umProjeto();

        try {
            this.movementBankService.atualizaProjetoNaturezaPeloNaturezaResumo(null, projeto);
            verifyNoInteractions(this.projetoNaturezaRepositoryMock);
        } catch (Exception e) {

            Assert.fail("Não deveria lançar excessão =>" + e.getMessage());

        }
    }

    public ProjetoNatureza umProjetoNatureza() {
        ProjetoNatureza projetoNatureza= new ProjetoNatureza();
        projetoNatureza.setId(1L);
        projetoNatureza.setProjeto(this.umProjeto());
        projetoNatureza.setNaturePlan(this.umNaturePlan());

        return projetoNatureza;
    }
    @Test
    public void atualizaProjetoNaturezaPeloNaturezaResumoParametroFakeProjetoNovo() {

        ProjetoNatureza projetoNatureza= this.umProjetoNatureza();
        ArgumentCaptor<ProjetoNatureza> arg = ArgumentCaptor.forClass(ProjetoNatureza.class);

        projetoNatureza.setValorPrevisto(new Double(1200));

        NaturezaResumo naturezaResumo = new NaturezaResumo();
        naturezaResumo.setValorTotal(1200);
        naturezaResumo.setNaturePlan(projetoNatureza.getNaturePlan());

        try {
           // when(this.projetoNaturezaRepositoryMock.findByIdProjetoIdNaturePlan(projetoNatureza.getProjeto().getId(),naturezaResumo.getNaturePlan().getId())).thenReturn(null);

            this.movementBankService.atualizaProjetoNaturezaPeloNaturezaResumo(naturezaResumo,projetoNatureza.getProjeto());
            verify(this.projetoNaturezaRepositoryMock).save(arg.capture());

            ProjetoNatureza projetoNaturezaExpected = arg.getValue();

            Assert.assertEquals(projetoNaturezaExpected.getNaturePlan(),projetoNatureza.getNaturePlan());
            Assert.assertEquals(projetoNaturezaExpected.getValorPrevisto(),projetoNatureza.getValorPrevisto());
            Assert.assertEquals(projetoNaturezaExpected.getProjeto(),projetoNatureza.getProjeto());

        } catch (Exception e){

              Assert.fail("não deveria lancar excessao " + e.getMessage());


        }
    }
    @Test
    public void atualizaProjetoNaturezaPeloNaturezaResumoParametroFakeProjetoExistente() {

        ProjetoNatureza projetoNatureza= this.umProjetoNatureza();
        ArgumentCaptor<ProjetoNatureza> arg = ArgumentCaptor.forClass(ProjetoNatureza.class);




        projetoNatureza.setValorPrevisto(new Double(100));

        NaturezaResumo naturezaResumo = new NaturezaResumo();
        naturezaResumo.setValorTotal(1200);
        naturezaResumo.setNaturePlan(projetoNatureza.getNaturePlan());

        try {
           // when(this.projetoNaturezaRepositoryMock.findByIdProjetoIdNaturePlan(projetoNatureza.getProjeto().getId(),naturezaResumo.getNaturePlan().getId())).thenReturn(projetoNatureza);

            this.movementBankService.atualizaProjetoNaturezaPeloNaturezaResumo(naturezaResumo,projetoNatureza.getProjeto());
            verify(this.projetoNaturezaRepositoryMock).save(arg.capture());

            ProjetoNatureza projetoNaturezaExpected = arg.getValue();

            Assert.assertEquals(projetoNaturezaExpected.getNaturePlan(),projetoNatureza.getNaturePlan());
            Assert.assertEquals(new Double(1200),projetoNaturezaExpected.getValorPrevisto());
            Assert.assertEquals(projetoNaturezaExpected.getProjeto(),projetoNatureza.getProjeto());

        } catch (Exception e){

            Assert.fail("não deveria lancar excessao " + e.getMessage());


        }
    }
    @Test
    public void atualizaProjetoNaturezaPeloNaturezaResumoProjetoExistenteValorAbsolutoMaior() {

        ProjetoNatureza projetoNatureza = this.umProjetoNatureza();


        projetoNatureza.setValorPrevisto(new Double(2000));

        NaturezaResumo naturezaResumo = new NaturezaResumo();
        naturezaResumo.setValorTotal(1200);
        naturezaResumo.setNaturePlan(projetoNatureza.getNaturePlan());

        try {
           // when(this.projetoNaturezaRepositoryMock.findByIdProjetoIdNaturePlan(projetoNatureza.getProjeto().getId(), naturezaResumo.getNaturePlan().getId())).thenReturn(projetoNatureza);

            this.movementBankService.atualizaProjetoNaturezaPeloNaturezaResumo(naturezaResumo, projetoNatureza.getProjeto());
            verify(this.projetoNaturezaRepositoryMock,times(0)).saveAndFlush(any(ProjetoNatureza.class));


        } catch (Exception e) {

            Assert.fail("não deveria lancar excessao " + e.getMessage());


        }
    }
    @Test
    public void atualizaProjetoNaturezaPeloNaturezaResumoProjetoExistenteValorAbsolutoMaiorRealizadoNegativo() {
        ProjetoNatureza projetoNatureza = this.umProjetoNatureza();
        ArgumentCaptor<ProjetoNatureza> arg = ArgumentCaptor.forClass(ProjetoNatureza.class);


        projetoNatureza.setValorPrevisto(new Double(-2000));

        NaturezaResumo naturezaResumo = new NaturezaResumo();
        naturezaResumo.setValorTotal(-1200);
        naturezaResumo.setNaturePlan(projetoNatureza.getNaturePlan());

        try {
           // when(this.projetoNaturezaRepositoryMock.findByIdProjetoIdNaturePlan(projetoNatureza.getProjeto().getId(), naturezaResumo.getNaturePlan().getId())).thenReturn(projetoNatureza);

            this.movementBankService.atualizaProjetoNaturezaPeloNaturezaResumo(naturezaResumo, projetoNatureza.getProjeto());
            verify(this.projetoNaturezaRepositoryMock,times(0)).saveAndFlush(any(ProjetoNatureza.class));


        } catch (Exception e) {

            Assert.fail("não deveria lancar excessao " + e.getMessage());


        }
    }
    @Test
    public void atualizaProjetoNaturezaPeloNaturezaResumoProjetoExistenteValorAbsolutoMaiorNegativoRealizadoPositivo() {
        ProjetoNatureza projetoNatureza = this.umProjetoNatureza();
        ArgumentCaptor<ProjetoNatureza> arg = ArgumentCaptor.forClass(ProjetoNatureza.class);

        projetoNatureza.setValorPrevisto(new Double(-2000));

        NaturezaResumo naturezaResumo = new NaturezaResumo();
        naturezaResumo.setValorTotal(1200);
        naturezaResumo.setNaturePlan(projetoNatureza.getNaturePlan());

        try {
           // when(this.projetoNaturezaRepositoryMock.findByIdProjetoIdNaturePlan(projetoNatureza.getProjeto().getId(), naturezaResumo.getNaturePlan().getId())).thenReturn(projetoNatureza);

            this.movementBankService.atualizaProjetoNaturezaPeloNaturezaResumo(naturezaResumo, projetoNatureza.getProjeto());
            verify(this.projetoNaturezaRepositoryMock,times(0)).saveAndFlush(any(ProjetoNatureza.class));


        } catch (Exception e) {

            Assert.fail("não deveria lancar excessao " + e.getMessage());


        }
    }
    @Test
    public void atualizaProjetoNaturezaPeloNaturezaResumoProjetoExistenteValorAbsolutoMaiorNegativoRealizadoNegativoMaior() {
        ProjetoNatureza projetoNatureza = this.umProjetoNatureza();
        ArgumentCaptor<ProjetoNatureza> arg = ArgumentCaptor.forClass(ProjetoNatureza.class);





        projetoNatureza.setValorPrevisto(new Double(-2000));

        NaturezaResumo naturezaResumo = new NaturezaResumo();
        naturezaResumo.setValorTotal(-2500);
        naturezaResumo.setNaturePlan(projetoNatureza.getNaturePlan());

        try {
           // when(this.projetoNaturezaRepositoryMock.findByIdProjetoIdNaturePlan(projetoNatureza.getProjeto().getId(), naturezaResumo.getNaturePlan().getId())).thenReturn(projetoNatureza);

            this.movementBankService.atualizaProjetoNaturezaPeloNaturezaResumo(naturezaResumo, projetoNatureza.getProjeto());
            verify(this.projetoNaturezaRepositoryMock).save(arg.capture());

            ProjetoNatureza projetoNaturezaRealizado = arg.getValue();

            Assert.assertEquals(projetoNatureza.getNaturePlan(), projetoNaturezaRealizado.getNaturePlan());
            Assert.assertEquals(new Double(-2500), projetoNaturezaRealizado.getValorPrevisto());
            Assert.assertEquals(projetoNatureza.getProjeto(), projetoNaturezaRealizado.getProjeto());

        } catch (Exception e) {

            Assert.fail("não deveria lancar excessao " + e.getMessage());


        }
    }
    @Test
    public void atualizaProjetoPeloRealizadoParametroNulo() {
        try {
            this.movementBankService.atualizaProjetoPeloRealizado(null);
            verify(this.projetoNaturezaRepositoryMock,times(0)).saveAndFlush(any(ProjetoNatureza.class));
        }  catch (Exception e) {
            Assert.fail("Não deveriar lançar excessao "+ e.getMessage());
        }
    }
    @Test
    public void atualizaProjetoPeloRealizadoParametroSemProjeto() {
        FiltroMovimentoBancario filtroMovimentoBancario = new FiltroMovimentoBancario();

        try {
            this.movementBankService.atualizaProjetoPeloRealizado(filtroMovimentoBancario);
            verify(this.projetoNaturezaRepositoryMock,times(0)).saveAndFlush(any(ProjetoNatureza.class));
        }  catch (Exception e) {

            Assert.fail("Não deveriar lançar excessao "+ e.getMessage());
        }
    }
    public WorkSpace umWorkSpace() {
        WorkSpace workSpace = new WorkSpace();
        workSpace.setId(1L);
        workSpace.nome("WORKSPACE");
        return workSpace;
    }
    public FiltroMovimentoBancario umFiltroMovimentoBancario(){
        FiltroMovimentoBancario filtroMovimentoBancario = new FiltroMovimentoBancario();
        filtroMovimentoBancario.setProjetoId(this.umProjeto().getId());
        filtroMovimentoBancario.setDataInicial( ZonedDateTime.now());
        filtroMovimentoBancario.setDataFinal( ZonedDateTime.now());
        filtroMovimentoBancario.setWorkSpaceId(this.umWorkSpace().getId());
        return filtroMovimentoBancario;
    }

    @Test
    public void atualizaProjetoPeloRealizadoParametroComProjetoComValor() {
        FiltroMovimentoBancario filtroMovimentoBancario = this.umFiltroMovimentoBancario() ;
        ArgumentCaptor<ProjetoNatureza> argumentCaptor = ArgumentCaptor.forClass(ProjetoNatureza.class);
        Projeto projeto = this.umProjeto();
        Optional<Projeto> oprojeto = Optional.of(projeto);
        List<NaturePlan> naturePlanList = new ArrayList<NaturePlan>();
        naturePlanList.add(this.umNaturePlan());
        NaturezaResumo naturezaResumo = new NaturezaResumo();

            when(this.projetoRepositoryMock.findById(projeto.getId())).thenReturn(oprojeto);
            when(this.naturePlanRepositoryMock.findByWorkSpaceId(filtroMovimentoBancario.getWorkSpaceId())).thenReturn(naturePlanList);
            when(this.movementBankRepositoryMock.totalDaNatureza(
                filtroMovimentoBancario.getWorkSpaceId(),
                filtroMovimentoBancario.getDataInicial(),
                filtroMovimentoBancario.getDataFinal(),
                naturePlanList.get(0).getId())).thenReturn(new Double(2000));

            this.movementBankService.atualizaProjetoPeloRealizado(filtroMovimentoBancario);
            verify(this.projetoNaturezaRepositoryMock).save(argumentCaptor.capture());
            verify(this.projetoNaturezaRepositoryMock,times(1)).save(any(ProjetoNatureza.class));

            ProjetoNatureza projetoNatureza = argumentCaptor.getValue();

            Assert.assertEquals(new Double(2000),projetoNatureza.getValorPrevisto());

    }
    @Test
    public void atualizaProjetoPeloRealizadoParametroComProjetoComValorZero() {
        FiltroMovimentoBancario filtroMovimentoBancario = this.umFiltroMovimentoBancario();
        List<NaturePlan> naturePlanList = new ArrayList<NaturePlan>();
        naturePlanList.add(this.umNaturePlan());
        NaturezaResumo naturezaResumo = new NaturezaResumo();
        Projeto projeto = this.umProjeto();
        Optional<Projeto> oprojeto = Optional.of(projeto);
        when(this.projetoRepositoryMock.findById(filtroMovimentoBancario.getProjetoId())).thenReturn(oprojeto);
        when(this.naturePlanRepositoryMock.findByWorkSpaceId(filtroMovimentoBancario.getWorkSpaceId())).thenReturn(naturePlanList);
        when(this.movementBankRepositoryMock.totalDaNatureza(
            filtroMovimentoBancario.getProjetoId(),
            filtroMovimentoBancario.getDataInicial(),
            filtroMovimentoBancario.getDataFinal(),
            naturePlanList.get(0).getId())).thenReturn(new Double(0));

        this.movementBankService.atualizaProjetoPeloRealizado(filtroMovimentoBancario);

        verify(this.projetoNaturezaRepositoryMock,times(0)).saveAndFlush(any(ProjetoNatureza.class));


    }

}
