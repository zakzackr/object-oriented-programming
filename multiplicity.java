import java.util.ArrayList;

// 従業員は1-2つの会社に勤める(多重度1..2)
class Employee{
    private Company mainJob;
    private Company secondJob;

    public Employee(Company mainJob, Company secondJob){
        this.mainJob = mainJob;
        this.secondJob = secondJob;
    }
}

// class内でコンストラクタが存在しない場合、デフォルトコンストラクタがcompilerによって実行される（引数を取らず中身が空: public Company(){}）
class Company{
    // 会社は任意の数の従業員を雇うことができる(多重度*)
    private ArrayList<Employee> employees = this.employees = new ArrayList<>();
    // 会社は1-10人の役員によって経営される(多重度1..10)
    private BoardMember[] boardMembers = new BoardMember[10];
    // 会社は親会社を持つ場合も、持たない場合もある
    private Company parentCompany;
    // 会社は任意の数の子会社を持つ
    private ArrayList<Company> subsidiaries = new ArrayList<>();

    public void addEmployee(Employee employee){
        this.employees.add(employee);
    }

    public void setBoardMember(BoardMember boardMember, int position){
        this.boardMembers[position] = boardMember;
    }

    public void setParentCompany(Company parentCompany){
        this.parentCompany = parentCompany;
    }

    public void addSubsidiary(Company subsidiary){
        this.subsidiaries.add(subsidiary);
    }
}

// class内でコンストラクタが存在しない場合、デフォルトコンストラクタがcompilerによって実行される（public BoardMember(){}）
class BoardMember{
    // ひとりの役員は1-5社の経営に携わることができる(多重度1..5)
    private Company[] companiesManaging = new Company[5];

    public void setCompany(Company company, int position){
        this.companiesManaging[position] = company;
    }
}

class Main{
    public static void main(String[] args){
        Company company1 = new Company();
        Company company2 = new Company();

        // この従業員は本業で会社1、副業で会社2に勤める
        Employee employee = new Employee(company1, company2);
        company1.addEmployee(employee);
        company2.addEmployee(employee);


        // この役員は会社1,2の経営に携わる
        BoardMember boardMember = new BoardMember();
        company1.setBoardMember(boardMember, 0);
        company2.setBoardMember(boardMember, 0);
        boardMember.setCompany(company1, 0);
        boardMember.setCompany(company2, 1);

        // 会社1の子会社は会社2
        company1.addSubsidiary(company2);
        company1.setParentCompany(company1);
    }
}

