@Service
public class HostServiceImpl implements HostService {

    private HostRepository hostRepository;

    public HostServiceImpl() {}

    @Autowired
    public void setHostRepository(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public Host createHost(Host host) {
        return hostRepository.save(host);
    }

    @Override
    public Host getHost(Long id) {
        return hostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Host not found with id: " + id));
    }

    @Override
    public List<Host> getAllHosts() {
        return hostRepository.findAll();
    }
}
