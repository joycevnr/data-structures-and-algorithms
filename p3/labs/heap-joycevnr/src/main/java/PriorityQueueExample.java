/**
 * Exemplo demonstrando o uso da Fila de Prioridade
 */
public class PriorityQueueExample {
    
    public static void main(String[] args) {
        exemploHospital();
        System.out.println("\n" + "=".repeat(50) + "\n");
        exemploProcessos();
    }
    
    /**
     * Exemplo: Sistema de atendimento hospitalar
     */
    public static void exemploHospital() {
        System.out.println("🏥 SISTEMA HOSPITALAR");
        System.out.println("Prioridades: 1=Rotina, 5=Normal, 8=Urgente, 10=Emergência");
        
        PriorityQueue hospital = new PriorityQueue(10);
        
        // Chegada de pacientes
        System.out.println("\n📝 Chegada de pacientes:");
        hospital.enqueue(3);  // Consulta normal
        System.out.println("Paciente consulta (prioridade 3) chegou");
        
        hospital.enqueue(10); // Emergência!
        System.out.println("Paciente EMERGÊNCIA (prioridade 10) chegou");
        
        hospital.enqueue(1);  // Exame de rotina
        System.out.println("Paciente exame (prioridade 1) chegou");
        
        hospital.enqueue(8);  // Urgência
        System.out.println("Paciente urgência (prioridade 8) chegou");
        
        hospital.enqueue(5);  // Normal
        System.out.println("Paciente normal (prioridade 5) chegou");
        
        System.out.println("\n🔍 Próximo a ser atendido: " + hospital.peek());
        System.out.println("Fila atual: " + hospital);
        
        // Atendimento por prioridade
        System.out.println("\n👨‍⚕️ Ordem de atendimento:");
        while (!hospital.isEmpty()) {
            int prioridade = hospital.dequeue();
            String tipo = getTipoPaciente(prioridade);
            System.out.println("Atendendo: " + tipo + " (prioridade " + prioridade + ")");
        }
    }
    
    /**
     * Exemplo: Sistema de processos do sistema operacional
     */
    public static void exemploProcessos() {
        System.out.println("💻 SISTEMA OPERACIONAL");
        System.out.println("Prioridades: 1=Baixa, 5=Normal, 8=Alta, 10=Crítica");
        
        // Inicializar com alguns processos
        int[] processosIniciais = {5, 2, 8, 1, 7};
        PriorityQueue scheduler = new PriorityQueue(processosIniciais);
        
        System.out.println("\n⚙️ Processos no scheduler: " + scheduler);
        
        // Chegam mais processos
        System.out.println("\n📥 Novos processos chegando:");
        scheduler.enqueue(10); // Processo crítico
        System.out.println("Processo CRÍTICO (prioridade 10) adicionado");
        
        scheduler.enqueue(3);  // Processo normal
        System.out.println("Processo normal (prioridade 3) adicionado");
        
        System.out.println("\n🔍 Próximo processo: " + scheduler.peek());
        
        // Execução de alguns processos
        System.out.println("\n⚡ Executando 3 processos:");
        for (int i = 0; i < 3 && !scheduler.isEmpty(); i++) {
            int prioridade = scheduler.dequeue();
            String tipo = getTipoProcesso(prioridade);
            System.out.println("Executando: " + tipo + " (prioridade " + prioridade + ")");
        }
        
        System.out.println("\n📊 Processos restantes: " + scheduler.size());
    }
    
    private static String getTipoPaciente(int prioridade) {
        if (prioridade >= 10) return "EMERGÊNCIA";
        if (prioridade >= 8) return "Urgência";
        if (prioridade >= 5) return "Normal";
        if (prioridade >= 3) return "Consulta";
        return "Rotina";
    }
    
    private static String getTipoProcesso(int prioridade) {
        if (prioridade >= 10) return "CRÍTICO";
        if (prioridade >= 8) return "Alta prioridade";
        if (prioridade >= 5) return "Normal";
        if (prioridade >= 3) return "Baixa prioridade";
        return "Background";
    }
}
